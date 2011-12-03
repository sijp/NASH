/*
 * Segment.h
 *
 *  Created on: Dec 02, 2011
 *      Author: shlomi
	Author: nadavi
 */

#include "../include/Segment.h"

Segment::Segment(Mat &imageFile, Rect &rectFile) : image(imageFile), rect(rectFile) , RED(0,0,255) ,BLUE(255,0,0) , winName("image")
{
	this->mask = Mat::ones(imageFile.size(), CV_8UC1);
	grabCut( this->image, this->mask, this->rect, this->bgdModel, this->fgdModel, 0, GC_INIT_WITH_RECT );
	
}



// Each element in the mask is an 8-bit unsigned char (i.e., a byte) - called CV_8UC1
void Segment::getBinMask()
{
	if( this->mask.empty() || this->mask.type()!=CV_8UC1 )
	    CV_Error( CV_StsBadArg, "mask is empty or has incorrect type (not CV_8UC1)" );
	if( this->binMask.empty() || this->binMask.rows!=this->mask.rows || this->binMask.cols!=this->mask.cols )
	    this->binMask.create( this->mask.size(), CV_8UC1 );
	this->binMask = this->mask & 1;
}

// uchar is an 8-bit unsigned char (a byte)
void Segment::changeMask()
{
        vector<Point>::const_iterator it = this->bgdPixels.begin(), itEnd = this->bgdPixels.end();
        for( ; it != itEnd; ++it )
            this->mask.at<uchar>(*it) = GC_BGD;
        it = this->fgdPixels.begin(), itEnd = this->fgdPixels.end();
        for( ; it != itEnd; ++it )
            this->mask.at<uchar>(*it) = GC_FGD;
}

void Segment::showImage()
{
	Mat res1;
        if(this->mask.empty() )
            this->image.copyTo(res1);
        else
        {
            getBinMask();
            this->image.copyTo( res1, this->binMask);
        }
     
        vector<Point>::const_iterator it;
        for( it = this->bgdPixels.begin(); it != this->bgdPixels.end(); ++it )
            circle( res1, *it, 1, BLUE );
        for( it = this->fgdPixels.begin(); it != this->fgdPixels.end(); ++it )
            circle( res1, *it, 1, RED );
     
        imshow( winName, res1 );
}

void Segment::onMouse( int event, int x, int y, int flags)
    {
        switch( event )
        {
        case CV_EVENT_LBUTTONDOWN:
            {
                bool isb = (flags & CV_EVENT_FLAG_CTRLKEY) != 0,
                    isf = (flags & CV_EVENT_FLAG_SHIFTKEY) !=0; 
                if (isb || isf)
                {
                    this->pixState = IN_PROCESS;
                }
            }
            break;
        case CV_EVENT_LBUTTONUP:
            if(this->pixState == IN_PROCESS )
            {
                this->pixState = NOT_SET;
                showImage();
            }
            break;
        case CV_EVENT_MOUSEMOVE:
            if( this->pixState == IN_PROCESS )
            {
                Point p = Point(x, y);
                if( flags & CV_EVENT_FLAG_CTRLKEY )
                {
                    this->bgdPixels.push_back(p);
                    showImage();
                }
                if( flags & CV_EVENT_FLAG_SHIFTKEY )
                {
                    this->fgdPixels.push_back( Point(x, y) );
                    showImage();
                }
            }
            break;
        }
    }
    
void wrappedOnMouse(int event, int x , int y , int flags , void* ptr)
{
	Segment* segptr = (Segment*)ptr;
	if (segptr != NULL)
		segptr->onMouse(event,x,y,flags);
}  
  
void Segment::show()
{
	namedWindow(this->winName.c_str(), CV_WINDOW_AUTOSIZE );
	this->showImage();
	//imshow(this->winName.c_str(),this->image);
	setMouseCallback( this->winName.c_str(), wrappedOnMouse, (void*)this );
	
	this->pixState = NOT_SET;
	this->iterCount = 0;
        for(;;)
        {
            int c = cvWaitKey(0);
            switch( (char) c )
            {
            case 'r':
                this->pixState = NOT_SET;
                this->iterCount = 0;
                this->bgdPixels.clear(); this->fgdPixels.clear();
                this->mask.release();
                cout << endl;
                assert( this->bgdPixels.empty() && this->fgdPixels.empty() && this->mask.empty() );
                showImage();
                break;
            case 'n':
            	cout << "<" << iterCount << "... ";
            	changeMask();
            	this->bgdPixels.clear(); this->fgdPixels.clear();
            	grabCut( this->image, this->mask, this->rect, this->bgdModel, this->fgdModel, 1 );
            	showImage();
            	cout << iterCount << ">" << endl;
            	iterCount++;
                break;
            case 'b':
            	cout<<"Initializing Blend operation shit"<<endl;
            	return;
            }
        }
	destroyWindow( winName.c_str() );
}

Mat& Segment::getImage()
{
	return this->image;
}

Mat& Segment::getMask()
{
	return this->mask;
}


