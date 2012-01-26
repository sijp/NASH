/*
 * GraphicProcesser.cpp
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#include "../include/GraphicProcess.h"
#include <iostream>
using namespace std;

GraphicProcess::GraphicProcess():image(NULL),actions()
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
	unsigned int i;
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
	unsigned int i;
	for (i=0;i<actions.size();i++)
	{
		delete actions[i];
	}
}

void GraphicProcess::print()
{
	cout<<"actions:"<<endl;
	unsigned int i;
	for (i=0;i<this->actions.size();i++)
	{
		cout<<i<<":";
		actions[i]->getName();
	}
}

	GraphicProcess::GraphicProcess(const GraphicProcess &obj):image(obj.image),actions()
	{
		
	}
	GraphicProcess &GraphicProcess::operator=(const GraphicProcess &obj)
	{
		this->image=obj.image;
		return *(this);
	}
