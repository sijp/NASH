#ifndef JOBXMLPARSER_H_
#define JOBXMLPARSER_H_

#include <Poco/DOM/DOMParser.h>
#include <Poco/DOM/NodeIterator.h>
#include <Poco/DOM/NodeFilter.h>
#include <Poco/DOM/AutoPtr.h>
#include <Poco/DOM/Document.h>
#include <Poco/DOM/Node.h>
#include <Poco/DOM/ElementsByTagNameList.h>
#include <Poco/DOM/NamedNodeMap.h>
#include <Poco/DOM/DocumentFragment.h>
#include <vector>

#include "../include/Job.h"
#include "../include/GraphicAction.h"


using namespace std;

/**
 *
 * JobXMLParser: parses an XML string into a Job object.
 * you mush initialize it then call parseXML, parseDocument and only then getJob to get the job.
 *
 */

class JobXMLParser
{
	private:
		std::string XMLData;
		Poco::XML::AutoPtr<Poco::XML::Document> fDom;
		Job job;
	public:
		JobXMLParser();
		/**
		 *
		 * sets the XMLData to the string specified
		 *
		 */
		JobXMLParser(string);
		/**
		 *
		 * parses the document object model
		 *
		 */
		void parseDocument();
		/**
		 *
		 * gets the job
		 *
		 */
		Job &getJob();
		/**
		 *
		 * parses the XML into a DOM object
		 *
		 */
		void parseXML();
		/**
		 *
		 * adds an effect from the <effectlist> tag in the XML
		 *
		 */
		void addEffects(Poco::XML::Node* docIteratorNode);
		~JobXMLParser();
		
		/**
		 *
		 * analyze the GaussianBlur tag
		 *
		 */
		GraphicAction *getGaussianBlurAction(Poco::XML::Node* docIteratorNode);
		/**
		 *
		 * analyze the Resize tag
		 *
		 */
		GraphicAction *getResizeAction(Poco::XML::Node* docIteratorNode);
		/**
		 *
		 * analyze the Grayscale tag
		 *
		 */
		
		GraphicAction *getGrayAction(Poco::XML::Node* docIteratorNode);

};

#endif
