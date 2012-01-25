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

/**
 *
 * GraphicProcess: this class stores an image and a vector of GrapihcAction's which once the process 
 * method is called adds the effects specified in the GraphicAction's vectors in a sequential manner.
 *
 */

class GraphicProcess
{
	
	private:
		Mat *image;
		vector<GraphicAction *> actions;
	public:
		GraphicProcess();
		/**
		 *
		 * sets the internal image
		 *
		 */
		GraphicProcess(const GraphicProcess &);
		GraphicProcess &operator=(const GraphicProcess &);
		void setImage(Mat* img);
		/**
		 *
		 * adds a new effect to the list of effects
		 *
		 */
		void addAction(GraphicAction *a);
		/**
		 *
		 * process the effects
		 *
		 */
		void process(Mat &dst);
		~GraphicProcess();
		void print();
};

#endif /* GRAPHICPROCESSER_H_ */
