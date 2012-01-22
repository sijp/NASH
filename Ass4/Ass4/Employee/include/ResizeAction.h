/*
 * ResizeAction.h
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#ifndef RESIZEACTION_H_
#define RESIZEACTION_H_

#include "../include/GraphicAction.h"

using namespace cv;

/**
 *
 * ResizeAction: resizes an image
 * inherits from GraphicAction, see GraphicAction for more details
 *
 */

class ResizeAction:public GraphicAction
{
	private:
		Size size;
		double factorX;
		double factorY;
		int interpolation;
	public:
		ResizeAction(Size dsize, double fx=0, double fy=0, int i=INTER_LINEAR);
		virtual void process(const Mat &src,Mat &dst);
		virtual ~ResizeAction();
		virtual void getName();
};

#endif /* GRAYACTION_H_ */
