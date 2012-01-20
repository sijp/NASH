/*
 * GraphicProcesser.h
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#ifndef GRAPHICPROCESSER_H_
#define GRAPHICPROCESSER_H_

#include "../include/GraphicAction.h"
#include <opencv2/core/mat.hpp>
#include <vector>

using namespace cv;
using namespace std;

class GraphicProcess
{
	
	private:
		Mat &image;
		vector<GraphicAction *> actions;
	public:
		GraphicProcess(Mat &m);
		void addAction(GraphicAction *a);
		virtual void process(Mat &dst);
};

#endif /* GRAPHICPROCESSER_H_ */
