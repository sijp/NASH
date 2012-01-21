/*
 * HttpLineInterperter.h
 *
 *  Created on: Jan 20, 2012
 *      Author: nadav
 */

#ifndef HTTPLINEINTERPERTER_H_
#define HTTPLINEINTERPERTER_H_

#include <string>

using namespace std;

class HttpLineInterperter
{
	private:
		/*
		 * holds the information from the http response
		 */
		string resStatus,
			resServer,
			resResource,
			resContentType;
		int resContentLength;

	public:
		/*
		 * init a new HttpLineInterperter object
		 */
		HttpLineInterperter();
		/*
		 * insert a line from the http response into the appropiate string
		 */
		void insertLine(string line);

		/*
		 *
		 */
		string getStatus();
		/*
		 *
		 */
		string getServer();
		/*
		 *
		 */
		string getResource();
		/*
		 *
		 */
		string getContentType();
		/*
		 *
		 */
		int getContentLength();

};

#endif /* HTTPLINEINTERPERTER_H_ */
