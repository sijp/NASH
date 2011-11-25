/*
 * FaceDetectorGui.h
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi
 * 	Author: nadavi
 */

#ifndef FACEDETECTORGUI_H_
#define FACEDETECTORGUI_H_

#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <string>
#include "../include/FaceDetector.h"
using namespace cv;
using namespace std;

class FaceDetectorGui
{
  private:
    //FaceDetector an object that detects faces into a rectangles.
    FaceDetector detector;
    string linuxTitle;
    vector<Rect> faces;
		vector<Rect> bodies;
    Mat result;
  public:
    FaceDetectorGui(const string &imgName ,const string &linuxTitle);
    bool show();
    bool save(const string &targetFile);
};



#endif /* FACEDETECTORGUI_H_ */
