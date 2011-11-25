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
class Segment
{
	private:
		Mat image;
		Rect roi;
		vector<Point> bgdPixels;
		vector<Point> fgdPixels;
	public:
		Segment(Mat , Rect);
		void changeMask(vector<Point> bgdPixels , vector<Point> fgdPixels);
		Mat &getImage();
}; //nadavi


#endif
