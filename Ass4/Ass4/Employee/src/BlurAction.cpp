/*
 * BlurAction.cpp
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#include "../include/BlurAction.h"
#include <opencv2/imgproc/imgproc.hpp>
#include <iostream>
using namespace std;
using namespace cv;


BlurAction::BlurAction(Size ks,double sx,double sy, int bt):ksize(ks),sigmaX(sx),sigmaY(sy),borderType(bt)
{
	cout<<"BlurAction:"<<sx<<","<<sy<<","<<bt<<endl;
}

void BlurAction::process(const Mat &src,Mat &dst)
{
	GaussianBlur(src,dst,this->ksize,this->sigmaX,this->sigmaY,this->borderType);
}

BlurAction::~BlurAction()
{
}

void BlurAction::getName()
{
	cout<<"BlurAction"<<endl;
}

