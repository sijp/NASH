/*
 * BlurAction.h
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#ifndef BLURACTION_H_
#define BLURACTION_H_

#include "../include/GraphicAction.h"

using namespace cv;

/**
 *
 * BlurAction : uses openCV's GaussianBlur to blur images.
 *
 */

class BlurAction:public GraphicAction
{
	private:
		/*
		 * the 'Kernel Size'
		 */
		Size ksize;
		/*
		 * SigmaX parameter for blurring
		 */
		double sigmaX;
		/*
		 * SigmaY Parameter for blurring
		 */
		double sigmaY;
		/*
		 * the border type. see openCV's docs for available border types
		 */
		int borderType;
	public:
		/*
		 * sets the parameters needed for the blurring
		 */
		BlurAction(Size,double,double,int);
		/*
		 * process the src image and saves the result in the dst image.
		 * the src image is saved intact
		 */
		virtual void process(const Mat &src,Mat &dst);
		virtual ~BlurAction();
		virtual void getName();
};

#endif /* BLURACTION_H_ */
