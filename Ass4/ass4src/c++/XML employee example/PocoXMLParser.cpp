#include "PocoXMLParser.h"

PocoXMLParser::PocoXMLParser(void)
{
}


PocoXMLParser::~PocoXMLParser(void)
{
	for (int i=0;i<fEmployees.size();i++)
		delete fEmployees.at(i);
}

PocoXMLParser::PocoXMLParser(std::string xmlFileName):fXMLFileName(xmlFileName)
{
}

std::vector<Employee*> PocoXMLParser::getParsedXML(){
	return fEmployees;
}

void PocoXMLParser::parseXmlFile(){
	//get the factory
	Poco::XML::DOMParser parser;
	//filters white space nodes
	parser.setFeature(parser.FEATURE_FILTER_WHITESPACE, true);
	//parse using builder to get fDom representation of the XML file
	fDom = parser.parse(fXMLFileName);

}

void PocoXMLParser::parseDocument(){

	Poco::XML::NodeIterator docIterator(Poco::XML::NodeIterator(fDom, Poco::XML::NodeFilter::SHOW_ALL));
	Poco::XML::Node* docIteratorNode;

	docIteratorNode = docIterator.nextNode();
	while (docIteratorNode){
		if (docIteratorNode->nodeName() == "Employee"){
			Employee *e = getEmployee(docIteratorNode);
			//add it to list
			fEmployees.push_back(e);
		}
		docIteratorNode = docIterator.nextNode();
	}
}

/**
* I take an employee element and read the values in, create
* an Employee object and return it
*/
Employee* PocoXMLParser::getEmployee(Poco::XML::Node* docIteratorNode) {

	//for each <employee> element get text or int values of
	//name ,id, age and name
	Poco::XML::AutoPtr<Poco::XML::NodeList> childrenList = docIteratorNode->childNodes();

	std::string name = childrenList->item(0)->innerText();
	int id; std::istringstream(childrenList->item(1)->innerText()) >> id;
	int age; std::istringstream(childrenList->item(2)->innerText()) >> age;
	Poco::XML::AutoPtr<Poco::XML::NamedNodeMap> attributeMap = docIteratorNode->attributes();
	std::string type = attributeMap->getNamedItem("type")->innerText();
	//Create a new Employee with the value read from the xml nodes
	Employee *e = new Employee(name,id,age,type);
	return e;
}


