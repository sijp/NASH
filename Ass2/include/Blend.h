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
 *	then it generates a new "Blended Image" which shows a new image
 *	with the background image and with the forgroud image on top of it.
 */

class Blend
{
	private:
		/*
		 * the background image for the blending process
		 */
		Mat bg_image;
		/*
		 * the foreground image for the blending process
		 */
		Mat fg_image;
		/*
		 * the image mask for the blending process, used for 
		 * determining which pixel to put where.
		 */
		Mat mask;
		/*
		 * the image mask for the blending process, used for 
		 * determining which pixel to put where.
		 */
		Mat blended_image;
		/*
		 * This method adds the forground image on top of the
		 * background image and saves it in the blended_image property.
		 */
		void process();
	public:
		/*
		 * Constructor
		 * bg- the background image
		 * fg- the foreground image
		 * msk- the mask used for the blending process
		 */
		Blend(Mat &bg,Mat &fg,Mat &msk);
		/*
		 * returns the final blended image.
		 */
		Mat &getBlendedImage();
		/*
		 * shows the blended image in a new window
		 */
		void show();
		/*
		 * saves the blended image to `filename` file
		 */
		bool save(const string &filename);
};

#endif
