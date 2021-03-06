#include "../include/JobXMLParser.h"
#include "../include/GrayAction.h"
#include "../include/BlurAction.h"
#include "../include/ResizeAction.h"

JobXMLParser::JobXMLParser():XMLData(),fDom(),job()
{
}


JobXMLParser::~JobXMLParser()
{

}

JobXMLParser::JobXMLParser(string xml):XMLData(xml),fDom(),job()
{
}

Job &JobXMLParser::getJob()
{
	return this->job;
}

void JobXMLParser::parseXML(){
	//get the factory
	Poco::XML::DOMParser parser;
	//filters white space nodes v1.4.2p
	//parser.setFeature(parser.FEATURE_FILTER_WHITESPACE, true);
	//filter white space nodes v1.3.0 - uni version
	parser.setFeature(Poco::XML::DOMParser::FEATURE_WHITESPACE,false);
	//parse using builder to get fDom representation of the XML file
	fDom = parser.parseString(this->XMLData);

}

/*
<?xml version="1.0" encoding="UTF-8"?>
<JobRequest>
<InputRepresentation="3"/>
<OutputRepresentation="8"/>
<effectsList>

<cvtColor>
<code> CV_RGB2GRAY </code>
</cvtColor>
<resize>
<scaleFactorX> 0.5 </scaleFactorX>
<scaleFactorY> 0.5 </scaleFactorY>
<interpolation> INTER_LINEAR </interpolation>
</resize>
<GaussianBlur>
<kSize> 3 </kSize>
<sigmaX> 2 </sigmaX>
<sigmaY> 2 </sigmaY>
<borderType> BORDER_REPLICATE </borderType>
</GaussianBlur>

</effectList>
</JobRequest>

*/

void JobXMLParser::parseDocument()
{
	string iRep="";
	string oRep="";
	
	
	Poco::XML::NodeIterator docIterator(Poco::XML::NodeIterator(fDom, Poco::XML::NodeFilter::SHOW_ALL));
	Poco::XML::Node* docIteratorNode;

	docIteratorNode = docIterator.nextNode();
	while (docIteratorNode)
	{
		if (docIteratorNode->nodeName() == "InputRepresentation")
		{
			iRep=((Poco::XML::Element *)docIteratorNode)->getAttribute("id");

		}
		else if (docIteratorNode->nodeName() == "OutputRepresentation")
			oRep=((Poco::XML::Element *)docIteratorNode)->getAttribute("id");
		else if (docIteratorNode->nodeName() == "effectsList")
			this->addEffects(docIteratorNode);
		docIteratorNode = docIterator.nextNode();
	}
	this->job.setRepDownload(iRep);
	this->job.setRepUpload(oRep);
}

void JobXMLParser::addEffects(Poco::XML::Node* docIteratorNode)
{
	Poco::XML::NodeList * effects = docIteratorNode->childNodes();
	unsigned int i;
	for (i=0;i<effects->length();i++)
	{
		GraphicAction *ga;
		Poco::XML::Node *effect=effects->item(i);
		cout<<"fx:"<<effect->nodeName()<<endl;
		if(effect->nodeName() == "cvtColor")
			ga=this->getGrayAction(effect);
		else if(effect->nodeName() == "resize")
			ga=this->getResizeAction(effect);
		else if(effect->nodeName() == "GaussianBlur")
			ga=this->getGaussianBlurAction(effect);
		this->job.addEffect(ga);
		
	}
}

GraphicAction* JobXMLParser::getGrayAction(Poco::XML::Node* docIteratorNode)
{
	string code;
	Poco::XML::AutoPtr<Poco::XML::NodeList> childrenList = docIteratorNode->childNodes();
	if (childrenList->item(0)->nodeName() == "code")
	{
		code=childrenList->item(0)->firstChild()->getNodeValue();
		return new GrayAction(CV_RGB2GRAY);
	}
	return NULL;
}

GraphicAction *JobXMLParser::getResizeAction(Poco::XML::Node* docIteratorNode)
{
	double Fx,Fy;
	string inter;
	int interValue=INTER_LINEAR;
	Poco::XML::AutoPtr<Poco::XML::NodeList> props = docIteratorNode->childNodes();
	unsigned int i;
	for (i=0;i<props->length();i++)
	{
		Poco::XML::Node *prop=props->item(i);
		if (prop->nodeName()=="scaleFactorX")
			Fx=atof(prop->firstChild()->getNodeValue().c_str());
		else if (prop->nodeName()=="scaleFactorY")
			Fy=atof(prop->firstChild()->getNodeValue().c_str());
		else if (prop->nodeName()=="interpolation")
			inter=prop->firstChild()->getNodeValue();
		
	}
	
	if (inter=="INTER_NEAREST")
		interValue=INTER_NEAREST;
	else if (inter=="INTER_LINEAR")
		interValue=INTER_LINEAR;
	else if (inter=="INTER_AREA")
		interValue=INTER_AREA;
	else if (inter=="INTER_CUBIC")
		interValue=INTER_CUBIC;
	else if (inter=="INTER_LANCZOS4")
		interValue=INTER_LANCZOS4;
		
	return new ResizeAction(Size(0,0), Fx,Fy, interValue);
	
}

GraphicAction *JobXMLParser::getGaussianBlurAction(Poco::XML::Node* docIteratorNode)
{
	Size ksize;
	int Sx,Sy;
	string borderType;
	int borderTypeValue;
	Poco::XML::AutoPtr<Poco::XML::NodeList> props = docIteratorNode->childNodes();
	unsigned int i;
	for (i=0;i<props->length();i++)
	{

		Poco::XML::Node *prop=props->item(i);
		if (prop->nodeName()=="kSize")
		{
			int ks=atoi(prop->firstChild()->getNodeValue().c_str());
			ksize=Size(ks,ks);
		}
		else if (prop->nodeName()=="sigmaX")
			Sx=atoi(prop->firstChild()->getNodeValue().c_str());
		else if (prop->nodeName()=="sigmaY")
			Sy=atoi(prop->firstChild()->getNodeValue().c_str());
		else if (prop->nodeName()=="borderType")
			borderType=prop->firstChild()->getNodeValue();
	}
	borderTypeValue=BORDER_CONSTANT;
	if (borderType=="BORDER_CONSTANT")
		borderTypeValue=BORDER_CONSTANT;
	else if (borderType=="BORDER_DEFAULT")
		borderTypeValue=BORDER_DEFAULT;
	else if (borderType=="BORDER_ISOLATED")
		borderTypeValue=BORDER_ISOLATED;
	else if (borderType=="BORDER_REFLECT")
		borderTypeValue=BORDER_REFLECT;
	else if (borderType=="BORDER_REFLECT_101")
		borderTypeValue=BORDER_REFLECT_101;
	else if (borderType=="BORDER_REPLICATE")
		borderTypeValue=BORDER_REPLICATE;
	else if (borderType=="BORDER_TRANSPARENT")
		borderTypeValue=BORDER_TRANSPARENT;
	else if (borderType=="BORDER_WRAP")
		borderTypeValue=BORDER_WRAP;
		
	return new BlurAction(ksize,Sx,Sy,borderTypeValue);
}


