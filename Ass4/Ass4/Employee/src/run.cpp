#include "../include/JobXMLParser.h"
#include "../include/Job.h"
#include <string>

#include <opencv2/imgproc/imgproc.hpp>

using namespace std;

int main()
{
	Employee e("192.168.2.105",8080);
	e.connect();
	e.run();
	e.close();
	
}
