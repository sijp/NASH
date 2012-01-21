/*
 * Job.cpp
 *
 *  Created on: Jan 21, 2012
 *      Author: nadav
 */

#include "../include/Job.h"

		/*
		 * init a new job.
		 */
		Job::Job()
		{

		}

		/*
		 * downloads the image from the manager' from the urlDownload
		 */
		bool Job::download(Employee &employee, HttpLineInterperter *resConfiguration)
		{
			string downloadRequest = "GET /photos/" +
									resConfiguration->getResource() +
									"?rep=" + this->repDownload +
									" HTTP/1.1\n";
			employee.send(downloadRequest);
			HttpLineInterperter downResponsetInterperter;
			string s;
			while(employee.getLineAscii(s) && s.size()>1)
				{
					downResponsetInterperter.insertLine(s);
				}
			//if we succeded in downloading the image
			if(downResponsetInterperter.getStatus() == "200")
			{
				//init a new byte array in the size of the content length
				int byteLength = atoi(downResponsetInterperter.getContentLength().c_str());
				char* dataByte = new char[byteLength];
				employee.getBytes(dataByte , byteLength);
				//dataBytes holds the bytes for the image
				this->mimeType = downResponsetInterperter.getContentType();
				vector<uchar> vecByte(dataByte , dataByte + sizeof(dataByte)/sizeof(uchar));
				//-1 as a flag means we get the image as is.
				imdecode(Mat(vecByte) , -1);

				this->gP.setImage(&(this->image));

				return true;
			}
			return false;
		}

		/*
		 *calls the GraphicProcess process method
		 */
		void Job::process()
		{
			this->gP.process(this->editedImage);
		}

		/*
		 * uploads the edited image to the urlUpload
		 */
		void Job::upload(Employee &employee , HttpLineInterperter *resConfiguration)
		{
			string uploadRequest = "PUT /photos/" +
					resConfiguration->getResource() +
					"?rep=" + this->repUpload +
					" HTTP/1.1";
			employee.send(uploadRequest);
			employee.send("Host: " + resConfiguration->getServer());
			employee.send("Content-Type: " + this->mimeType);
			employee.send("Content-Length: " + this->editedImage.elemSize());
			employee.send("");

			/*
			 * sending the edited image in bytes
			 */
			int pos = this->mimeType.find("/");
			string imgExt = this->mimeType.substr(pos+1);
			vector<uchar> vecByte;
			imencode(imgExt , this->editedImage , vecByte);
			char* buf = vecByte.data();
			employee.sendBytes(buf , this->editedImage.elemSize());
		}

		/*
		 * set the url for downloading the image
		 */
		void Job::setRepDownload(string rep)
		{
			this->repDownload = rep;
		}

		/*
		 * set the url for aploading the edited image
		 */
		void Job::setRepUpload(string rep)
		{
			this->repUpload = rep;
		}
