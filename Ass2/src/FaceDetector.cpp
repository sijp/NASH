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
	return faces;
}




