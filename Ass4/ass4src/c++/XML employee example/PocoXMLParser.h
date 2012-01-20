#include <Poco/DOM/DOMParser.h>
#include <Poco/DOM/NodeIterator.h>
#include <Poco/DOM/NodeFilter.h>
#include <Poco/DOM/AutoPtr.h>
#include <Poco/DOM/Document.h>
#include <Poco/DOM/Node.h>
#include <Poco/DOM/ElementsByTagNameList.h>
#include <Poco/DOM/NamedNodeMap.h>
#include <Poco/DOM/DocumentFragment.h>
#include "Employee.h"
#include <istream>
#include <vector>
#include <iostream>

class PocoXMLParser
{
private:
	std::string fXMLFileName;
	Poco::XML::AutoPtr<Poco::XML::Document> fDom;
	std::vector<Employee*> fEmployees;
	Employee* getEmployee(Poco::XML::Node*);

public:
	PocoXMLParser(void);
	PocoXMLParser(std::string);

	void parseDocument();
	std::vector<Employee*> getParsedXML();
	void parseXmlFile();
	~PocoXMLParser(void);
};

