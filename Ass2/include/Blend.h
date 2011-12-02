/*
 * Blend.h
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi
	Author: nadavi
 */


#ifndef BLEND_H_
#define BLEND_H_

#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <string>

using namespace std;
using namespace cv;

/*
 *	Class Blend:
 *	Resposible for taking a background image a foreground image and a mask.
 *	then it generates a new "Blended Image" which shows a combination of all
 
 *
 */

class Blend
{
	private:
		Mat bg_image;
		Mat fg_image;
		Mat mask;
		Mat blended_image;
		void process();
	public:
		Blend(Mat &bg,Mat &fg,Mat &msk);
		Mat &getBlendedImage();
};

#endif
