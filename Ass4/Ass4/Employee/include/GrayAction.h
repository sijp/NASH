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

class GrayAction:public GraphicAction
{
	private:
		int code;
	public:
		GrayAction(int);
		virtual void process(const Mat &src,Mat &dst);
		virtual ~GrayAction();
};

#endif /* GRAYACTION_H_ */
