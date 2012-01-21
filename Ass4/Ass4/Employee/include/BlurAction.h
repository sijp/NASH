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
		int borderType;
	public:
		BlurAction(Size,double,double,int);
		virtual void process(const Mat &src,Mat &dst);
		virtual ~BlurAction();
		virtual void getName();
};

#endif /* BLURACTION_H_ */
