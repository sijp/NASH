#include "../include/Blend.h"

Blend::Blend(Mat &bg,Mat &fg,Mat &msk):bg_image(bg),fg_image(fg),mask(msk)
{
	process();
}

void Blend::process()
{
	this->bg_image.copyTo(this->blended_image);
	Mat t=this->blended_image(Rect(this->bg_image.size().width/2-this->fg_image.size().width/2,this->bg_image.size().height-this->fg_image.size().height,this->fg_image.size().width,this->fg_image.size().height));
	this->fg_image.copyTo(t,this->mask);
}

Mat &Blend::getBlendedImage()
{
	return this->blended_image;
}

void Blend::show()
{
	namedWindow("Blended Image",CV_WINDOW_NORMAL);
	imshow("Blended Image",this->getBlendedImage());
	cvWaitKey();
	destroyWindow("Blended Image");
}

bool Blend::save(const string &filename)
{
	if (this->blended_image.empty())
		return false;
	imwrite(filename, this->blended_image);
	return true;
}


