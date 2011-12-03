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
		Mat image;
		Mat mask;
		Mat res;
		Mat binMask;
		Mat bgdModel, fgdModel;
		userState pixState;
		Rect rect;
		vector<Point> bgdPixels;
		vector<Point> fgdPixels;
		Point p1, p2;
		const Scalar RED;
    		const Scalar BLUE;
    		const string winName;
    		int iterCount;
	public:
		Segment(Mat& , Rect&);
		void getBinMask();
		void onMouse( int event, int x, int y, int flags);
		void changeMask();
		void showImage();
		void show();
		Mat &getImage();
		Mat &getMask();
}; //nadavi

void wrappedOnMouse(int event, int x , int y , int flags , void* ptr);

#endif
