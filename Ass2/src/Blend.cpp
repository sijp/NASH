#include "../include/Blend.h"

Blend::Blend(Mat &bg,Mat &fg,Mat &msk):bg_image(bg),fg_image(fg),mask(msk)
{
	process();
}

void Blend::process()
{
	this->bg_image.copyTo(this->blended_image);
	this->fg_image.copyTo(this->blended_image,this->mask);
}

Mat &Blend::getBlendedImage()
{
	return this->blended_image;
}

int main(int argc,char **argv)
{
	Mat img1,img2,mask;
	img1=imread("./bg.png");
	img2=imread("./fg.png");
	mask=imread("./mask.png");
	Blend b(img1,img2,mask);
	namedWindow("W",1);
	imshow("W",b.getBlendedImage());
	cvWaitKey();
	destroyWindow("W");
	
	return 0;
}