#include "Employee.h"
#include <iostream>

Employee::Employee(void)
{
	fName = "";
	fAge = 0;
	fId = 0;
	fType = "";
}

Employee::Employee(std::string name, int age, int id, std::string type):fName(name), fAge(age), fId(id), fType(type)
{
}

Employee::~Employee(void)
{
}

std::string Employee::toString()
{
	std::stringstream ss;
	ss << "Employee\n\tName:" << fName << "\n\tAge:" << fAge << "\n\tId:" << fId << "\n\tType:" << fType << "\n"; 
	return ss.str();
}