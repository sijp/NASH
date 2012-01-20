/*
 * GraphicAction.h
 *
 *  Created on: Jan 20, 2012
 *      Author: shlomi
 */

#ifndef GRAPHICACTION_H_
#define GRAPHICACTION_H_



class GraphicAction
{
	public:
		virtual void process() =0;
		virtual ~GraphicAction() =0;
};



#endif /* GRAPHICACTION_H_ */
