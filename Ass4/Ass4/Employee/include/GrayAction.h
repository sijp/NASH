/*
 * GrayAction.h
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#ifndef GRAYACTION_H_
#define GRAYACTION_H_

#include "../include/GraphicAction.h"
#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;

/**
 *
 * turns the image into grayscale
 * inherits from GraphicAction, see GraphicAction for more details
 *
 */

class GrayAction:public GraphicAction
{
	private:
		int code;
	public:
		GrayAction(int);
		virtual void process(const Mat &src,Mat &dst);
		virtual ~GrayAction();
		virtual void getName();
};

#endif /* GRAYACTION_H_ */
