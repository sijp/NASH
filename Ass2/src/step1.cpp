/*
 * step1.cpp
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi, nadavi
 */
#include "../include/FaceDetector.h"
#include "../include/FaceDetectorGui.h"
#include <iostream>
using namespace std;

int main(int argc , char *argv[])
{
	if (argc != 3)
	{
		cout<<"this program gets exactly two parameters"<<endl;
		return 1;
	}
	FaceDetectorGui fdg(argv[1] , "Face Detector");
	fdg.show();
	fdg.save(argv[2]);
	return 0;
}
