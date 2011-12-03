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

void Blend::show()
{
	namedWindow("Blended Image",1);
	imshow("Blended Image",this->getBlendedImage());
	cvWaitKey();
	destroyWindow("Blended Image");
}


