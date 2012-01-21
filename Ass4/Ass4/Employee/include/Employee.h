//============================================================================
// Name        : Employee.h
// Author      : nadav and shlomi
// Version     :
// Copyright   : Your copyright notice
// Description :
//============================================================================


#ifndef EMPLOYEE_H_
#define EMPLOYEE_H_

#include <iostream>
#include <string>
#include <Poco/Net/StreamSocket.h>
#include <Poco/Net/SocketStream.h>
#include <Poco/Net/SocketAddress.h>
#include <Poco/Exception.h>

#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/core/core.hpp>
//#include <opencv2/highgui/highgui.hpp>
#include <opencv2/opencv.hpp>


#include "../include/HttpLineInterperter.h"

using namespace std;
using namespace cv;

class Employee
{
	private:
		Poco::Net::SocketAddress sa_;
		Poco::Net::StreamSocket socket_;
		Poco::Net::SocketStream inputStream_;
		bool toRun;

	public:
		const short port_;
		const string host_;

		/*
		 * init a new employee
		 */
		Employee(const string & host , const short port);

		/*
		 * a loop that checks if there is a job waiting
		 * if yes, excecute it, if not wait for 30 seconds
		 */
		void run();

		/*
		 * get a job description in an xml representation
		 */
		char *getJob (HttpLineInterperter *);

		/*
			 *gets the xml, decode it and process it
		 */
		void doJob(char * , HttpLineInterperter *);

		/*
		 * connecting to the manager, using port_ and host_;
		 */
		bool connect();

		/*
		 * read a fixed number of bytes from the server
		 * returns false in case the connection is not ready.
		 */
		bool getBytes(char frame[] , int bytesToRead);

		/*
		 *
		 */
	    // Read an Ascii line from the server
	    // Returns false in case connection closed before a newline can be read.
	    bool getLineAscii(string& line);

	    // Get Ascii data from the server until the null character
	    // Returns false in case connection closed before null can be read.
	    bool getFrameAscii(string& frame);

	    // Send message to the remote host.
	    // Returns false in case connection is closed before all the data is sent.
	    bool send(const string& message);

		// Send a fixed number of bytes from the client - blocking.
	    // Returns false in case the connection is closed before bytesToWrite bytes can be read.
	    bool sendBytes(const char frame[], int bytesToWrite);

	    // Close down the connection properly.
	    void close();

	    /*
	     * destructor
	     */
	    virtual ~Employee();
	    /*
	     *
	     */

};

#endif /* EMPLOYEE_H_ */
