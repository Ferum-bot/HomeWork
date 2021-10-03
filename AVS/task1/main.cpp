#include <iostream>
#include <fstream>
#include <cstdlib>
#include <cstring>
#include <ctime>

#include "src/container/Container.h"

void invalidParametersList() {
    std::cout << "Invalid input parameters!\n";
    std::cout << "Expected:\n";
    std::cout << "-f <input_file> <out_file_1> <out_file_2> \n";
    std::cout << "Or: \n";
    std::cout << "-g <intput_file> <out_file_1> <out_file_2>";
}

void invalidFlagAlias() {
    std::cout << "Invalid input flag alias!\n";
    std::cout << "Expected:\n";
    std::cout << "-f or -g";
}

void processStarted() {
    std::cout << "Process started.\n";
}

void containerFilled() {
    std::cout << "Container filled.\n";
}

void inputPrinted() {
    std::cout << "Input printed.\n";
}

void containerSorted() {
    std::cout << "Container sorted.\n";
}

void resultPrinted() {
    std::cout << "Result printed.\n";
}

void processFinished() {
    std::cout << "Process finished.\n";
}

void initAndFillContainer(Container& container, char* argv[]) {
    int size = 0;

    if (std::strcmp(argv[1], "-f") == 0) {
        std::ifstream input(argv[2]);
        input >> size;

        init(container, size);
        inputFromStream(container, input);
        return;
    }

    if (std::strcmp(argv[1], "-g") == 0) {
        std::ifstream input(argv[2]);
        input >> size;

        init(container, size);
        srand(time(nullptr));
        randomInput(container);
        return;
    }

    invalidFlagAlias();
    exit(2);
}

int main(int argc, char* argv[]) {
    if (argc != 5) {
        invalidParametersList();
        return 1;
    }

    Container container;
    initAndFillContainer(container, argv);

    containerFilled();

    std::ofstream firstOutput(argv[3]);
    std::ofstream secondOutput(argv[4]);
    outputToStream(container, firstOutput);
    inputPrinted();

    sortContainerData(container);
    containerSorted();
    outputToStream(container, secondOutput);
    resultPrinted();

    deInit(container);
    processFinished();
}