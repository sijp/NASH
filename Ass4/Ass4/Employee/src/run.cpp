#include "../include/JobXMLParser.h"
#include "../include/Job.h"
#include <string>
#include <iostream>

#include <opencv2/imgproc/imgproc.hpp>

using namespace std;

int main(int argc,char *argv[])
{
	if (argc!=3)
	{
		cout<<"arg mismatch"<<endl;
		return 1;
	}
	Employee e(argv[1],atoi(argv[2]));
	e.connect();
	e.run();
	e.close();
	
}
