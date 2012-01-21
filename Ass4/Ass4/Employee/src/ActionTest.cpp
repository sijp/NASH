#include "../include/JobXMLParser.h"
#include "../include/Job.h"
#include <string>

#include <opencv2/imgproc/imgproc.hpp>

using namespace std;

int main()
{
	Employee e("sijp.co.il",80);
	e.connect();
	e.run();
	e.close();
	
}
