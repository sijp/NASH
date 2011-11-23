/*
 * FaceDetector.h
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi
 */

#ifndef FACEDETECTOR_H_
#define FACEDETECTOR_H_
#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <string>
using namespace std;
using namespace cv;

class FaceDetector
{
	private:
		Mat image;
	public:
		//read the image into a cv::mat object.
		FaceDetector(const string &imgName);
		vector<Rect> getFaces();
		Mat &getImage();
};




#endif /* FACEDETECTOR_H_ */
