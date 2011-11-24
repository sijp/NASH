# All Targets
all: ./src/step1
# Tool invocations
# Executable "step1" depends on the files FaceDetector.o, FaceDetectorGui.o and step1.cpp.

./src/step1: ./src/FaceDetector.o ./src/FaceDetectorGui.o ./src/step1.cpp
	@echo "Building step1"
	g++ `pkg-config opencv --libs --cflags` ./src/FaceDetector.o ./src/FaceDetectorGui.o ./src/step1.cpp
./src/FaceDetectorGui.o: ./src/FaceDetector.o ./src/FaceDetectorGui.cpp ./include/FaceDetectorGui.h
	@echo "Building FaceDetectorGui.o"
	g++ -c ./src/FaceDetectorGui.cpp
./src/FaceDetector.o: ./src/FaceDetector.cpp ./include/FaceDetector.h
	@echo "Building FaceDetector.o"
	g++ -c ./src/FaceDetector.cpp



clean:
	rm-rf Debug/*