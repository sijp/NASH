/*
 * GraphicAction.h
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#ifndef GRAPHICACTION_H_
#define GRAPHICACTION_H_

#include <opencv2/core/core.hpp>
#include <opencv2/opencv.hpp>

using namespace cv;

class GraphicAction
{
	public:
		virtual void process(const Mat &src,Mat &dst)=0;
};



#endif /* GRAPHICACTION_H_ */
