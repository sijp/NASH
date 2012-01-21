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

using namespace std;

class JobXMLParser
{
	private:
		std::string XMLData;
		Poco::XML::AutoPtr<Poco::XML::Document> fDom;
		Job job;
	public:
		JobXMLParser();
		JobXMLParser(string);
		void parseDocument();
		Job &getJob();
		void parseXML();
		void addEffects(Poco::XML::Node* docIteratorNode);
		~JobXMLParser();
};

#endif
