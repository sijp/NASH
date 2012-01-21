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
				HttpLineInterperter resConfiguration;
				char *data = this->getJob(&resConfiguration);
				if(data == NULL)
					sleep(30000);
				else
					this->doJob(data , &resConfiguration);
			}
		}

		/*
			 * get a job description in an xml representation
		 */
		char* Employee::getJob(HttpLineInterperter *resConfiguration)
		{
			this->send("POST /jobs/get-new-job HTTP/1.1");
			this->send("");
			string s;
			while(this->getLineAscii(s) && s.size()>1)
				{
					resConfiguration->insertLine(s);
				}
			//init a new byte array in the size of the content length
			int byteLength = resConfiguration->getContentLength();
			char* dataByte = new char[byteLength];

			this->getBytes(dataByte , byteLength);
			return dataByte;

		}

		/*
		 *gets the xml, decode it and process it
		 */
		void Employee::doJob(char* data , HttpLineInterperter *resConfiguration)
		{
			//convert the bytes data into string
			//assuming the srcEncoding and encoding are the same type
			string strData(data , resConfiguration->getContentLength());
			JobXMLParser xmlDecoder(strData);
			xmlDecoder.parseXML();
			xmlDecoder.parseDocument();
			/*Job job = xmlDecoder.getJob();
			bool flag;
			flag = job.download(*(this) , *resConfiguration);
			if (flag)
			{
				job.process();
				job.upload(*(this) , *resConfiguration);
			}*/
		}

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
	    	char ch = '\0';
	    	//stop when we encounter a null character
	    	//notice that the null character is not appended to the frame string.
	    	try
	    	{
	    	this->socket_.receiveBytes(&ch , 1);
	    	while(ch !='\0')
	    	{
	    		frame.append(1 , ch);
	    		this->socket_.receiveBytes(&ch , 1);
	    	}
	    	}
	    	catch(Exception & error)
	    	{
	    		cout<<"receive faild (Error: " << error.msg << ")" << endl;
	    		return false;
	    	}
	    	return true;
	    }

	    // Send message to the remote host.
	    // Returns false in case connection is closed before all the data is sent.
	    bool Employee::send(const string& message)
	    {
	    	try
	    	{
	    		this->inputStream_ << message << endl << flush;
	    	}
	    	catch(Exception & error)
	    	{
	    		cout<<"Error while sending to a socket (Error: " << error.msg <<
	    				")" << endl;
	    		return false;
	    	}
	    	return true;
	    }

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
	    		return false;
	    	}
	    	return true;
	   	}

	    // Close down the connection properly.
	    void Employee::close()
	    {
	    	try
	    	{
	    		this->socket_.shutdown();
	    	}
	    	catch(Exception & error)
	    	{
	    		cout<<"closing faild, connection already closed"<<endl;
	    	}
	    }

	    /*
	     * destructor
	     */
	    Employee::~Employee()
	    {

	    }
