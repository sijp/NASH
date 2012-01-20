/*
 * BlurAction.cpp
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#include "../include/BlurAction.h"
#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;

BlurAction::BlurAction(Size ks,double sx,double sy):ksize(ks),sigmaX(sx),sigmaY(sy)
{
}

void BlurAction::process(const Mat &src,Mat &dst)
{
	GaussianBlur(src,dst,this->ksize,this->sigmaX,this->sigmaY);
}

BlurAction::~BlurAction()
{
}

