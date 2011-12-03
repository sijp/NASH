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
    /*
    *saves the window title
    */
    string linuxTitle;
    /*
    *a vector of rectangles keeps the faces rectangle
    */
    vector<Rect> faces;
    /*
    *a vector of rectangles keeps the bodies rectangle
    */
    vector<Rect> bodies;
    /*
    *holds the image with the rectangles
    */
    Mat result;
  public:
    /*
    *constructor, gets image name and a title name for the window
    *recognize the faces and bodies and insert them into the faces vector
    */
    FaceDetectorGui(const string &imgName ,const string &linuxTitle);
    /*
    *drews rectangle around the faces and bodies in the image
    *show the result in a window for 5 seconds
    */
    bool show();
    /*
	*saves the recognized faces and body image into the target file
	*/
    bool save(const string &targetFile);
    /*
	*return the first Rectangle of a body.
	*/
    Rect &getRectangle();
    /*
	*returns the original image
	*/
    Mat &getOriginalImage();
    bool isValid();
    virtual ~FaceDetectorGui();
};



#endif /* FACEDETECTORGUI_H_ */
