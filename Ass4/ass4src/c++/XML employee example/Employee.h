#include <string>
#include <sstream>

class Employee
{
private:		
	std::string fName;
	int fAge;
	int fId;
	std::string fType;


public:
	Employee(void);
	Employee(std::string, int, int, std::string);
	std::string toString();
	void parseXmlFile();
	~Employee(void);
};

