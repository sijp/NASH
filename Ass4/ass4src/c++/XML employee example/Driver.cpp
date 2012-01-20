#include "Driver.h"

int main(int argc, char** argv){

	PocoXMLParser xmlParser("employees.xml");
	xmlParser.parseXmlFile();
	xmlParser.parseDocument();
	std::vector<Employee*> employees = xmlParser.getParsedXML();
	printData(employees);
	return 0;
}


void printData(std::vector<Employee*> employees){

	std::cout << "Number of Employees '" << employees.size() << "'." << std::endl;

	std::vector<Employee*>::iterator it;
	for (it=employees.begin(); it!=employees.end(); it++)
		std::cout << (*it)->toString();
}
