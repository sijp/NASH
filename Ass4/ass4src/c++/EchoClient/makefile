CC:=g++
BIN_FOLDER:=bin
INCLUDE:=/
CFLAGS:=-c -Wall -Weffc++ -g 
LDFLAGS:=-L/usr/lib -lPocoNet -lPocoUtil -lPocoFoundation

all: bin/encoder.o EchoClient
	g++ $(LDFLAGS) bin/encoder.o bin/echoClientC.o bin/pocoClient.o -o bin/echoExample

bin/encoder.o: encoder/encoder.cpp
	$(CC) $(CFLAGS) encoder/encoder.cpp -o bin/encoder.o

EchoClient: bin/echoClientC.o bin/pocoClient.o
	

bin/echoClientC.o: echoClient/echoClientC.cpp
	$(CC) $(CFLAGS) echoClient/echoClientC.cpp -o bin/echoClientC.o 

bin/pocoClient.o: echoClient/pocoClient.cpp
	$(CC) $(CFLAGS) echoClient/pocoClient.cpp -o bin/pocoClient.o 

.PHONY: clean
clean:
	rm -f bin/*
