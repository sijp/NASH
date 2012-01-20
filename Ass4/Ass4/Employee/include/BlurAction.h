/*
 * BlurAction.h
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#ifndef BLURACTION_H_
#define BLURACTION_H_

#include "../include/GraphicAction.h"

class BlurAction:public GraphicAction
{
	public:
		virtual void process();
		virtual ~BlurAction();
};

#endif /* BLURACTION_H_ */
