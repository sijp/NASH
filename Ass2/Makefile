# All Targets
all: ./bin/step1 ./bin/ass2
step1: ./bin/step1
ass2: ./bin/ass2
# Tool invocations
# Executable "step1" depends on the files FaceDetector.o, FaceDetectorGui.o and step1.cpp.

./bin/step1: ./bin/FaceDetector.o ./bin/FaceDetectorGui.o ./src/step1.cpp
	@echo "Building step1"
	g++ `pkg-config opencv --libs --cflags` ./bin/FaceDetector.o ./bin/FaceDetectorGui.o ./src/step1.cpp -o ./bin/step1
./bin/ass2: ./bin/FaceDetectorGui.o ./bin/Segment.o ./bin/Blend.o ./src/Run.cpp
	@echo "Building ass2"
	g++ `pkg-config opencv --libs --cflags`  ./bin/FaceDetector.o ./bin/FaceDetectorGui.o ./bin/Segment.o ./bin/Blend.o ./src/Run.cpp -o ./bin/ass2
./bin/Blend.o: ./src/Blend.cpp ./include/Blend.h
	@echo "Building Blend"
	g++ -c `pkg-config opencv --libs --cflags` ./src/Blend.cpp -o ./bin/Blend.o
./bin/Segment.o: ./src/Segment.cpp ./include/Segment.h
	@echo "Building Segment"
	g++ -c `pkg-config opencv --libs --cflags` ./src/Segment.cpp -o ./bin/Segment.o
./bin/FaceDetectorGui.o: ./bin/FaceDetector.o ./src/FaceDetectorGui.cpp ./include/FaceDetectorGui.h
	@echo "Building FaceDetectorGui.o"
	g++ -c `pkg-config opencv --libs --cflags` ./src/FaceDetectorGui.cpp -o ./bin/FaceDetectorGui.o
./bin/FaceDetector.o: ./src/FaceDetector.cpp ./include/FaceDetector.h
	@echo "Building FaceDetector.o"
	g++ -c `pkg-config opencv --libs --cflags` ./src/FaceDetector.cpp -o ./bin/FaceDetector.o



clean:
	rm -rf ./bin/*
