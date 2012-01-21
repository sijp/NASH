/*
 * Job.h
 *
 *  Created on: Jan 21, 2012
 *      Author: nadav
 */

#ifndef JOB_H_
#define JOB_H_

#include <iostream>
#include <string>

#include "../include/HttpLineInterperter.h"
#include "../include/Employee.h"
#include "../include/GraphicAction.h"
#include "../include/GraphicProcess.h"

using namespace std;

class Job
{
	private:
		string repDownload;
		string repUpload;
		Mat image;
		string mimeType;
		Mat editedImage;
		//GraphicProcess contains mat and vector of actions.
		GraphicProcess gP;
	public:
		/*
		 * init a new job.
		 */
		Job();

		/*
		 * downloads the image from the manager, using resource id and repdownload
		 */
		bool download(Employee &employee , HttpLineInterperter *resConfiguration);

		/*
		 *calls the GraphicProcess process method
		 */
		void process();

		/*
		 * uploads the edited image to the urlUpload using resource id and repdownload
		 */
		void upload(Employee &employee , HttpLineInterperter *resConfiguration);

		/*
		 * set the url for downloading the image
		 */
		void setRepDownload(string rep);

		/*
		 * set the url for aploading the edited image
		 */
		void setRepUpload(string rep);

		/*
		 * adds the action `action` to the GraphicProcess object
		 */
		void addEffect(GraphicAction *action);
		
		void print();
};



#endif /* JOB_H_ */
