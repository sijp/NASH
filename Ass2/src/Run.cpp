/*
* Run.cpp
*
* Created on : Dec 03, 2011
* 	Author: shlomi, nadavi
*/

#include "../include/FaceDetectorGui.h"
#include "../include/Segment.h"
#include "../include/Blend.h"
using namespace std;

int main(int argc , char* argv[])
{
	if(argc != 3)
	{
		cout<<"this program gets only two parameters"<<endl;
		return 1;
	}
	
	FaceDetectorGui fdg(argv[1] , "Face Detector");
	fdg.show();
	Segment imageSeg(fdg.getOriginalImage() , fdg.getRectangle());
	imageSeg.show();
	Mat bgImage = imread(argv[2]);
	namedWindow("check" , 1);
	imshow("check" , imageSeg.getMask());
	Blend imageBlender(bgImage , fdg.getOriginalImage() , imageSeg.getMask());
	imageBlender.show();
	 

	return 0;
}
