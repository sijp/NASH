# All Targets
all: ./employee
employee: ./employee
# Tool invocations
# Executable "step1" depends on the files FaceDetector.o, FaceDetectorGui.o and step1.cpp.

./employee: ./BlurAction.o ./Employee.o ./GraphicProcess.o ./GrayAction.o ./HttpLineInterperter.o ./Job.o ./JobXMLParser.o ./ResizeAction.o ./src/run.cpp
	@echo "Building employee executable"
	g++ -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++  ./BlurAction.o ./Employee.o ./GraphicProcess.o ./GrayAction.o ./HttpLineInterpreter.o ./Job.o ./JobXMLParser.o ./ResizeAction.o ./src/run.cpp -o employee
	
./JobXMLParser.o: ./src/JobXMLParser.cpp ./include/JobXMLParser.h 
	@echo "Building JobXMLParser"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/JobXMLParser.cpp -o ./JobXMLParser.o

./Job.o: ./src/Job.cpp ./include/Job.h 
	@echo "Building Job"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/Job.cpp -o ./Job.o

./GraphicProcess.o: ./src/GraphicProcess.cpp ./include/GraphicProcess.h 
	@echo "Building Job"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/GraphicProcess.cpp -o ./GraphicProcess.o 
	
./HttpLineInterperter.o: ./src/HttpLineInterperter.cpp ./include/HttpLineInterperter.h 
	@echo "Building HttpLineInterpreter"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/HttpLineInterperter.cpp -o ./HttpLineInterpreter.o

./Employee.o: ./src/Employee.cpp ./include/Employee.h 
	@echo "Building Employee class"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/Employee.cpp -o ./Employee.o	

./ResizeAction.o: ./src/ResizeAction.cpp ./include/ResizeAction.h 
	@echo "Building ResizeAction"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/ResizeAction.cpp -o ./ResizeAction.o	


./BlurAction.o: ./src/BlurAction.cpp ./include/BlurAction.h 
	@echo "Building BlurAction"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/BlurAction.cpp -o ./BlurAction.o	
	
./GrayAction.o: ./src/GrayAction.cpp ./include/GrayAction.h 
	@echo "Building GrayAction"
	g++ -c -I/usr/include -I../include/ -lPocoNet -lPocoFoundation -lPocoUtil -lPocoXML `pkg-config --cflags --libs opencv` -Wall -Weffc++ ./src/GrayAction.cpp -o ./GrayAction.o


	
	
	
clean:
	rm -rf ./bin/*
