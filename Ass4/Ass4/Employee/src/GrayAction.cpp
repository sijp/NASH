/*
 * BlurAction.cpp
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#include "../include/GrayAction.h"
#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;

GrayAction::GrayAction( int _code):code(_code)
{

}

void GrayAction::process(const Mat &src,Mat &dst)
{
	cvtColor(src,dst,this->code);
}

GrayAction::~GrayAction()
{
}

