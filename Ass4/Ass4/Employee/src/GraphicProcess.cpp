/*
 * GraphicProcesser.cpp
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#include "../include/GraphicProcesser.h"

GraphicProcess::GraphicProcess():image(NULL)
{
		
}

void GraphicProcess::addAction(GraphicAction *a)
{
	actions.push_back(a);
}

void GraphicProcess::setImage(Mat* img)
{
	this->image=img;
}

void GraphicProcess::process(Mat &dst)
{
	int i;
	Mat dst_tmp;
	dst=this->image->clone();
	for (i=0;i<actions.size();i++)
	{
		dst_tmp=dst.clone();
		actions[i]->process(dst_tmp,dst);
	}
}

GraphicProcess::~GraphicProcess()
{
	int i;
	for (i=0;i<actions.size();i++)
	{
		delete actions[i];
	}
}
