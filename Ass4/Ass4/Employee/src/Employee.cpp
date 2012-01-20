//============================================================================
// Name        : Employee.cpp
// Author      : nadav and shlomi
// Version     :
// Copyright   : Your copyright notice
// Description :
//============================================================================
#include "../include/Employee.h"

		/*
		 * init a new employee
		 */
		Employee::Employee(const string & host , const short port) : host_(host),
																	port_(port),
																	sa_(host,port),
																	socket_(),
																	inputStream_(socket_),
																	toRun(true)
		{

		}

		/*
		 * a loop that checks if there is a job waiting
		 * if yes, excecute it, if not wait for 30 seconds
		 */
		void Employee::run()
		{
			while(this->toRun)
			{
				char *data = this->getJob();
				if(data == NULL)
					sleep(30000);
				else
					this->doJob(data);
			}
		}

		/*
			 * get a job description in an xml representation
		 */
		char* Employee::getJob();

		/*
		 *gets the xml, decode it and process it
		 */
		void Employee::doJob(char* data);

		/*
		 * connecting to the manager, using port_ and host_;
		 */
		bool Employee::connect()
		{
			cout<<"Connecting to manager " <<
					this->sa_.host().toString() << ":"<<this->sa_.port() <<endl;
			try
			{
				this->socket_.connect(this->sa_); //sa_: socketAddress of the host
			}
			catch (Poco::Exception & error)
			{
				cout<<"connection failed"<<endl;
				return false;
			}
			return true;
		}

		/*
		 * read a fixed number of bytes from the server
		 * returns false in case the connection is not ready.
		 * in each iteration of the while loop, we read from the socket
		 * as much as we can into the frame[].
		 */
		bool Employee::getBytes(char frame[] , int bytesToRead)
		{
			int tmp = 0;
			try
			{
				while (bytesToRead > tmp)
				{
					//frame + tmp = &frame[tmp] , pointers arithmetics
					tmp = tmp + this->socket_.receiveBytes(frame + tmp , bytesToRead - tmp);
				}
			}
			catch(Exception & error)
			{
				cout<<"receive faild (Error: " << error.msg << ")" << endl;
				return false;
			}
			return true;
		}

		/*
		 *
		 */
	    // Read an Ascii line from the server
	    // Returns false in case connection closed before a newline can be read.
	    bool Employee::getLineAscii(string& line)
	    {
	    	try
	    	{
	    		getline(this->inputStream_ , line);
	    	}
	    	catch (Exception & error)
	    	{
	    		cout<<"receive faild (Error: " << error.msg << ")" << endl;
	    		return false;
	    	}
	    	return true;
	    }

	    // Get Ascii data from the server until the null character
	    // Returns false in case connection closed before null can be read.
	    bool Employee::getFrameAscii(string& frame)
	    {

	    }

	    // Send message to the remote host.
	    // Returns false in case connection is closed before all the data is sent.
	    bool Employee::send(const string& message);

		// Send a fixed number of bytes from the client - blocking.
	    // Returns false in case the connection is closed before bytesToWrite bytes can be read.
	    bool Employee::sendBytes(const char frame[], int bytesToWrite)
	    {
	    	int tmp = 0;
	    	try
	    	{
	    		while (bytesToWrite > tmp)
	    		{
	    			tmp = tmp + this->socket_.sendBytes(frame + tmp , bytesToWrite - tmp);
	    		}
	    	}
	    	catch(Exception & error)
	    	{
	    		cout<<"send failed (Error: " << error.msg <<")" <<endl;
	    	}
	   	}

	    // Close down the connection properly.
	    void Employee::close();

	    /*
	     * destructor
	     */
	    virtual Employee::~Employee();
