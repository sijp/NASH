/*
 * FaceDetector.h
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi
	Author: nadavi
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
		/*
		 * the image we use to detect faces
		 */
		Mat image;
	public:
		//read the image into a cv::mat object.
		FaceDetector(const string &imgName);
		/*
		 * searches for faces in the image.
		 */
		vector<Rect> getFaces();
		/*
		 * uses the faces method to approximate the bodies position.
		 */
		vector<Rect> getBodies();
		/*
		 * returns the source image 
		 */
		Mat &getImage();
		bool isValid();
		virtual ~FaceDetector();
};




#endif /* FACEDETECTOR_H_ */
