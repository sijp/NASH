/*
 * step1.cpp
 *
 *  Created on: Nov 22, 2011
 *      Author: shlomi
 */
#include "../include/FaceDetector.h"
#include <iostream>
using namespace std;

int main(int argc , char *argv[])
{
	if (argc != 2)
	{
		cout<<"this program gets exactly one parameter"<<endl;
		return 1;
	}

	FaceDetector detector(argv[1]);
	cout<<detector.getFaces().size()<<endl;

	return 0;
}
