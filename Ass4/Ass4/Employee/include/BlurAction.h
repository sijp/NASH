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

class BlurAction:public GraphicAction
{
	private:
		Size ksize;
		double sigmaX;
		double sigmaY;
	public:
		BlurAction(Size,double,double);
		virtual void process(const Mat &src,Mat &dst);
		virtual ~BlurAction();
};

#endif /* BLURACTION_H_ */
