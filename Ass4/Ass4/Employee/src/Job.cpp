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
		Job::Job():repDownload(),repUpload(),image(),mimeType(),editedImage(),gP()
		{

		}

		/*
		 * downloads the image from the manager' from the urlDownload
		 */
		bool Job::download(Employee &employee, HttpLineInterperter *resConfiguration)
		{
			string downloadRequest = "GET /photos/" +
									resConfiguration->getResource() +
									"/index.php?rep=" + this->repDownload +
									" HTTP/1.1";
			employee.send(downloadRequest);
			employee.send("Host: "+employee.getHost()+"\n");
			
			HttpLineInterperter downResponsetInterperter;
			string s;
			while(employee.getFrameAscii(s) && s.size()>1)
			{
				downResponsetInterperter.insertLine(s);
				s.clear();
			}
			//if we succeded in downloading the image
			if(downResponsetInterperter.getStatus() == "200 OK")
			{
				//init a new byte array in the size of the content length
				int byteLength = downResponsetInterperter.getContentLength();
				uchar* dataByte = new uchar[byteLength];
				employee.getBytes(dataByte , byteLength);
				//dataBytes holds the bytes for the image
				this->mimeType = downResponsetInterperter.getContentType();
				//cout<<dataByte<<endl;
				vector<uchar> vecByte(dataByte , dataByte + byteLength/sizeof(uchar));
				//-1 as a flag means we get the image as is.
				this->image=imdecode(Mat(vecByte) , -1);

				this->gP.setImage(&(this->image));
				delete [] dataByte;
				return true;
			}
			return false;
		}

		/*
		 *calls the GraphicProcess process method
		 */
		void Job::process()
		{
			cout<<"fdsfdsa"<<endl;
			imwrite("input.jpg",this->image);
			this->gP.process(this->editedImage);
			imwrite("output.jpg",this->editedImage);
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
			string imgExt = "."+this->mimeType.substr(pos+1);
			vector<uchar> vecByte;
			imencode(imgExt , this->editedImage , vecByte);
			uchar* buf = vecByte.data();
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

		void Job::addEffect(GraphicAction *action)
		{
			this->gP.addAction(action);
		}
		
		void Job::print()
		{
			cout<<repDownload<<endl<<repUpload<<endl;
			gP.print();
		}
