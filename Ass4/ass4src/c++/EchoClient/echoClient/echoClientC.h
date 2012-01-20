#ifndef ECHO_CLIENT__
#define ECHO_CLIENT__
                                           
#include <iostream>
#include <string>
#include "Poco/Net/StreamSocket.h"
#include "Poco/Net/SocketStream.h"
#include "Poco/Net/SocketAddress.h"
#include "Poco/Exception.h"
 
class EchoClient {
private:
    Poco::Net::SocketAddress sa_;
    Poco::Net::StreamSocket sock_;
    Poco::Net::SocketStream stream_;
 
public:
    const short port_;
    const std::string host_;
 
    EchoClient(std::string host, short port);
    virtual ~EchoClient();
 
    // Connect to the remote machine
    bool connect();
 
    // Read a fixed number of bytes from the server - blocking.
    // Returns false in case the connection is closed before bytesToRead bytes can be read.
    bool getBytes(char frame[], int bytesToRead);
 
    // Read an Ascii line from the server
    // Returns false in case connection closed before a newline can be read.
    bool getLineAscii(std::string& line);
 
    // Get Ascii data from the server until the null character
    // Returns false in case connection closed before null can be read.
    bool getFrameAscii(std::string& frame);
 
    // Send message to the remote host.
    // Returns false in case connection is closed before all the data is sent.
    bool send(const std::string& message);
	
	// Send a fixed number of bytes from the client - blocking.
    // Returns false in case the connection is closed before bytesToWrite bytes can be read.
    bool sendBytes(const char frame[], int bytesToWrite);
 
    // Close down the connection properly.
    void close();
 
}; //class EchoClient
 
#endif