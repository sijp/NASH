#include "../include/JobXMLParser.h"
#include "../include/Job.h"
#include <string>

#include <opencv2/imgproc/imgproc.hpp>

using namespace std;

int main()
{
	Mat m=imread("test.png");
	
	string xml;
	xml=string("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n") +
	    //"<JobRequest>\n" +
	    "<InputRepresentation=\"1\"/>\n"+
	    "<OutputRepresentation=\"2\"/>\n"+
	    "<effectsList>\n"+
	    "<effectsList>\n"+
	    "<cvtColor>\n"+
	    "<code> CV_RGB2GRAY </code>\n"+
	    "</cvtColor>\n"+
	    "<resize>\n"+
	    "<scaleFactorX> 0.5 </scaleFactorX>\n"+
	    "<scaleFactorY> 0.5 </scaleFactorY>\n"+
	    "<interpolation> INTER_LINEAR </interpolation>\n"+
	    "</resize>\n"+
	    "<GaussianBlur>\n"+
	    "<kSize> 3 </kSize>\n"+
	    "<sigmaX> 2 </sigmaX>\n"+
	    "<sigmaY> 2 </sigmaY>\n"+
	    "<borderType> BORDER_REPLICATE </borderType>\n"+
	    "</GaussianBlur>\n"+
	    "</effectsList>\n"+
	    "</JobRequest>";
	
	JobXMLParser parser(xml);
	
	parser.parseXML();
	parser.parseDocument();
	Job &job=parser.getJob();
	
	
//	imwrite("re.png",dst);
	
}
