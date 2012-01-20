// echoClientC.cpp
#include "echoClientC.h"
 
using Poco::Net::StreamSocket;
using Poco::Net::SocketStream;
using Poco::Net::SocketAddress;
using Poco::Exception;
 
using std::cin;
using std::cout;
using std::endl;
using std::string;
 
EchoClient::EchoClient(string host, short port):  sa_(host, port), sock_(), stream_(sock_), port_(port), host_(host){
}
    
EchoClient::~EchoClient() {
    close();
}
 
// Connects to a remote machine
bool EchoClient::connect() {
    // Initiate blocking connection with server.
    std::cout << "Starting connect to " 
        << sa_.host().toString() << ":" << sa_.port() << std::endl;
    try {
        sock_.connect(sa_);
    }
    catch (Exception error) {
        std::cout << "Connection failed (Error: " << error.displayText() << ')' << std::endl;
        return false;
    }
    return true;
}
 
// Keep waiting to get more bytes until we get exactly bytesToRead bytes into frame[]
// Assume frame[] is large enough to receive bytesToRead bytes.
bool EchoClient::getBytes(char frame[], int bytesToRead) {
    int tmp = 0;
    try {
        while ( bytesToRead > tmp ) {
            tmp += sock_.receiveBytes(frame + tmp, bytesToRead);
        }
    } catch (Exception error) {
        std::cout << "recv failed (Error: " << error.displayText() << ')' << std::endl;
        return false;
    }
    return true;
}

bool EchoClient::sendBytes(const char frame[], int bytesToWrite) {
    int tmp = 0;
    try {
        while ( bytesToWrite > tmp ) {
            tmp += sock_.sendBytes(frame + tmp, bytesToWrite);
        }
    } catch (Exception error) {
        std::cout << "recv failed (Error: " << error.displayText() << ')' << std::endl;
        return false;
    }
    return true;
}
 
// Read an Ascii line from the server
bool EchoClient::getLineAscii(std::string& line) {
    try {
        std::getline(stream_, line);
    } catch (Exception error) {
        std::cout << "recv failed (Error: " << error.displayText() << ')' << std::endl;
        return false;
    }
    return true;
}
 
// Read an Ascii frame until null character 
bool EchoClient::getFrameAscii(std::string& frame) {
    char ch = '\0';
    // Stop when we encounter the null character. 
    // Notice that the null character is not appended to the frame string.
    try {
        sock_.receiveBytes(&ch, 1);
        while (0 != ch) {
            frame.append(1, ch);
            sock_.receiveBytes(&ch, 1);
        }
    } catch (Exception error) {
        std::cout << "recv failed (Error: " << error.displayText() << ')' << std::endl;
        return false;
    }
    return true;
}
 
// Send data to the remote host through the socket stream
bool EchoClient::send(const std::string& message) {
    try {
        stream_ << message << std::endl << std::flush;
    } catch (Exception error) {
        std::cout << "Error while sending to a socket (Error:" 
                  << error.displayText() << ')' << std::endl;
        return false;
    }
    return true;
}
 
// Close down the connection properly.
void EchoClient::close() {
    try{
        sock_.shutdown();
    } catch (...) {
        std::cout << "closing failed: connection already closed" << std::endl;
    }
}