/*
 * BlurAction.cpp
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#include "../include/ResizeAction.h"
#include <opencv2/imgproc/imgproc.hpp>
#include <iostream>
using namespace std;
using namespace cv;

ResizeAction::ResizeAction(Size dsize, double fx, double fy, int i):size(dsize),factorX(fx),factorY(fy),interpolation(i)
{
}

void ResizeAction::process(const Mat &src,Mat &dst)
{
	resize(src,dst,this->size,this->factorX,this->factorY,this->interpolation);
}

ResizeAction::~ResizeAction()
{
}

void ResizeAction::getName()
{
	cout<<"ResizeAction"<<endl;
}

