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

/**
 *
 * GraphicAction: an abstract class used to be inherited from for processing images with OpenCV
 *
 */

class GraphicAction
{
	public:
		/**
		 *
		 * process the image src and saves it in dst
		 * it usually calls an OpenCV method to do the work
		 *
		 */
		virtual void process(const Mat &src,Mat &dst)=0;
		virtual void getName()=0;
		virtual ~GraphicAction(){}
};



#endif /* GRAPHICACTION_H_ */
