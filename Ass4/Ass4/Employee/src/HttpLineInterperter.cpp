/*
 * HttpLineInterperter.cpp
 *
 *  Created on: Jan 20, 2012
 *      Author: nadav
 */

#include <string>
#include <iostream>
#include <stdlib.h>
#include "../include/HttpLineInterperter.h"


	HttpLineInterperter::HttpLineInterperter():resStatus(""),
												resServer(""),
												resResource(""),
												resContentType(""),
												resContentLength(0)
	{

	}

	void HttpLineInterperter::insertLine(string line)
	{
		int pos = 0;
		pos = line.find(" ");
		string subStart = line.substr(0 , pos);
		string subEnd = line.substr(pos+1, line.size() - (pos + 1));

		cout<<pos<<":"<<subStart << " XXX " << subEnd<< endl;

		if (subStart =="HTTP/1.1")
			this->resStatus = subEnd;
		else if (subStart == "Server:")
			this->resServer = subEnd;
		else if (subStart == "Resource:")
			this->resResource = subEnd;
		else if (subStart == "Content-Type:")
			this->resContentType = subEnd;
		else if (subStart == "Content-Length:")
			this->resContentLength = atoi(subEnd.c_str());
	}
	/*
	 *
	 */
	string HttpLineInterperter::getStatus()
	{
		return this->resStatus;
	}
	/*
	 *
	 */
	string HttpLineInterperter::getServer()
	{
		return this->resServer;
	}
	/*
	 *
	 */
	string HttpLineInterperter::getResource()
	{
		return this->resResource;
	}
	/*
	 *
	 */
	string HttpLineInterperter::getContentType()
	{
		return this->resContentType;
	}
	/*
	 *
	 */
	int HttpLineInterperter::getContentLength()
	{
		return this->resContentLength;
	}
