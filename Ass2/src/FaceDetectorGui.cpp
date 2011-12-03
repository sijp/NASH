

#include "../include/FaceDetectorGui.h"

/*
*constructor, gets image name and a title name for the window
 recognize the faces and bodies and insert them into the faces vector
*/
FaceDetectorGui::FaceDetectorGui(const string &imgName ,const string &linuxTitle) : detector(imgName), linuxTitle(linuxTitle)
{
  this->faces = this->detector.getFaces();
  this->bodies = this->detector.getBodies();
  this->result = imread(imgName);
}

/*
*drews rectangle around the faces and bodies in the image
*show the result in a window for 5 seconds
*/
bool FaceDetectorGui::show()
{
  int i;
  if(this->result.empty())
    return false;
  for ( i = 0 ; i<this->faces.size() ; i++ )
  {
    Point p1 (this->faces[i].x , this->faces[i].y);
    Point p2 (this->faces[i].x + this->faces[i].width , this->faces[i].y + this->faces[i].height);
    rectangle(this->result, p1, p2, Scalar(255 , 0 , 255) , 2);
  }
  
  for ( i = 0 ; i<this->bodies.size() ; i++ )
  {
    cout<<i<<endl;
    Point p1 (this->bodies[i].x , this->bodies[i].y);
    Point p2 (this->bodies[i].x + this->bodies[i].width , this->bodies[i].y + this->bodies[i].height);
    rectangle(this->result, p1, p2, Scalar(0 , 0 , 255) , 2);
  }
 
  
  namedWindow(linuxTitle);
  imshow(this->linuxTitle , this->result);
	waitKey(5000);
	destroyWindow(this->linuxTitle);
  return true;
}

/*
*saves the recognized faces and body image into the target file
*/
bool FaceDetectorGui::save(const string &targetFile)
{
	if(this->result.empty())
		return false;
  	imwrite(targetFile , this->result);
  	return true;
}
/*
*return the first Rectangle of a body.
*/
Rect &FaceDetectorGui::getRectangle()
{
	if(this->bodies.size()==0)
		this->bodies.push_back(Rect(0,0,this->result.size().width,this->result.size().height));
	return this->bodies[0];
}

/*
*returns the original image
*/
Mat &FaceDetectorGui::getOriginalImage()
{
	return this->detector.getImage();	
}


FaceDetectorGui::~FaceDetectorGui()
{
	this->result.release();
}


