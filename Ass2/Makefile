# All Targets
all: ./bin/step1
# Tool invocations
# Executable "step1" depends on the files FaceDetector.o, FaceDetectorGui.o and step1.cpp.

./bin/step1: ./bin/FaceDetector.o ./bin/FaceDetectorGui.o ./src/step1.cpp
	@echo "Building step1"
	g++ `pkg-config opencv --libs --cflags` ./bin/FaceDetector.o ./bin/FaceDetectorGui.o ./src/step1.cpp -o step1
./bin/FaceDetectorGui.o: ./bin/FaceDetector.o ./src/FaceDetectorGui.cpp ./include/FaceDetectorGui.h
	@echo "Building FaceDetectorGui.o"
	g++ -c ./src/FaceDetectorGui.cpp -o ./bin/FaceDetectorGui.o
./bin/FaceDetector.o: ./src/FaceDetector.cpp ./include/FaceDetector.h
	@echo "Building FaceDetector.o"
	g++ -c ./src/FaceDetector.cpp -o ./bin/FaceDetector.o



clean:
	rm -rf ./bin/*
