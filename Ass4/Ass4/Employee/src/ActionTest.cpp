#include "../include/GraphicProcesser.h"
#include "../include/GrayAction.h"
#include "../include/BlurAction.h"
#include "../include/ResizeAction.h"

#include <opencv2/imgproc/imgproc.hpp>

int main()
{
	Mat m=imread("test.png");
	
	GrayAction ga(CV_RGB2GRAY);
	BlurAction ba(Size(5,5),5,5);
	Mat dst;
	
	
	GraphicProcess gp(m);
	gp.addAction(&ga);
	gp.addAction(&ba);
	
	gp.process(dst);
	
	imwrite("re.png",dst);
	
}
