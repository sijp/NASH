/*
 * FaceDetector.cpp
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi
 *      Author: nadavi
 */

#include "../include/FaceDetector.h"

//read the image into a cv::mat object.
FaceDetector::FaceDetector(const string &imgName)
{
	this->image = imread(imgName);
}
vector<cv::Rect> FaceDetector::getFaces()
{
	Mat grayImg;
	cvtColor(this->image,grayImg,CV_BGR2GRAY);
	// Load the model before it is used
	string face_cascade_name = "/usr/share/OpenCV/haarcascades/haarcascade_frontalface_alt.xml";
	CascadeClassifier face_cascade;
	face_cascade.load( face_cascade_name );
	vector<Rect> faces;
	face_cascade.detectMultiScale( grayImg, faces );
	grayImg.release();
	return faces;
}

vector<cv::Rect> FaceDetector::getBodies()
{
	vector<Rect> faces=this->getFaces();
	vector<Rect> bodies;
	int i;
	//for each face we approximate where the body is using average proportions.
	for (i = 0 ; i<faces.size(); i++)
	{
		int x=faces[i].x- faces[i].width;
		int y=faces[i].y - faces[i].height/2;
		int w=faces[i].width *3;
		int h=faces[i].height * 8;

		if (x<0)
			x=1;
		if (y<0)
			y=1;
		if (w+x>this->image.size().width)
			w=this->image.size().width-x-1;
		if (h+y>this->image.size().height)
			h=this->image.size().height-y-1;
		
		Rect r(x,y,w,h);
		bodies.push_back(r);		
	}
	return bodies;

}

Mat &FaceDetector::getImage()
{
	return this->image;
}

bool FaceDetector::isValid()
{
	return !this->image.empty();
}

FaceDetector::~FaceDetector()
{
	this->image.release();
}

