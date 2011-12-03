/*
 * Segment.h
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi
	Author: nadavi
 */


#ifndef SEGMENT_H
#define SEGMENT_H

#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <string>
using namespace std;
using namespace cv;

/*
Segment get an image and a Rect for the constructor
have an update function which recives pixels for the foreground and background image.
*/
typedef enum userState
{
	NOT_SET = 0,
	IN_PROCESS = 1,
	SET = 2,
}userState;

class Segment
{
	private:
		/*
		*holds the original image
		*/
		Mat image;
		/*
		*holds the mask of the image
		*/
		Mat mask;
		/*
		*holds the binary format of the mask image
		*/
		Mat binMask;
		/*
		*temporary values used for the grabCut operation
		*/		
		Mat bgdModel, fgdModel;
		/*
		*an indicator that save the situation of the pixel state
		*/
		userState pixState;
		/*
		*holds a rectangle of one body
		*/		
		Rect rect;
		/*
		*saves the background pixels for the change mask
		*/		
		vector<Point> bgdPixels;
		/*
		*saves the foreground pixels for the change mask
		*/		
		vector<Point> fgdPixels;		
		/*
		*saves the RGB value for red
		*/		
		const Scalar RED;
		/*
		**saves the RGB value for blue
		*/    		
    		const Scalar BLUE;
		/*
		*holds the winsow name
		*/    		
    		const string winName;
		/*
		*saves the numcer of iteration we call grabCut
		*/    		
    		int iterCount;
	public:
		/*
		*constructor, gets the original image and a rect that represent one body from the original image
		*/
		Segment(Mat& , Rect&);
		/*
		*making the binary file
		*/
		void makeBinMask();
		/*
		*holds the operation to do when we use the mouse
		*/
		void onMouse( int event, int x, int y, int flags);
		/*
		*updating the mask image
		*/
		void changeMask();
		/*
		*show the image in a image window
		*/
		void showImage();
		/*
		*the main function, with the user help, crop the image, making the binary mask file
		*/
		void show();
		/*
		*return a refrence to the original image
		*/
		Mat &getImage();
		/*
		*return the binary mask made for the foreground image
		*/
		Mat &getBinMask();
}; //nadavi

/*
*a function that helps us use the "onMouse" method in order to use the grabCut
*/
void wrappedOnMouse(int event, int x , int y , int flags , void* ptr);

#endif
