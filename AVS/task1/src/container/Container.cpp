#include "Container.h"

void init(Container& container, int size) {
    container.size = size;
    container.data = new Animal[size];
}

void deInit(Container& container) {
    delete container.data;
    container.size = 0;
}

void inputFromStream(Container& container, std::ifstream& input) {
    for (int i = 0; i < container.size; i++) {
        Animal animal;
        inputFromStream(animal, input);
    }
}

void randomInput(Container& container) {
    for (int i = 0; i < container.size; i++) {
        randomInput(container.data[i]);
    }
}

void outputToStream(Container& container, std::ofstream& output) {
    output << "The size of Container = " << container.size << "\n";
    for (int i = 0; i < container.size; i++) {
        outputToStream(container.data[i], output);
    }
}


void sortContainerData(Container& container) {
    const int size = container.size;
    for (int i = 0; i < size; i++) {
        double functionValue = calculateFunction(container.data[i]);
        int maxFunIndex = 0;

        for (int j = 0; j < size - i; j++) {
            if (calculateFunction(container.data[j]) > functionValue) {
                functionValue = calculateFunction(container.data[j]);
                maxFunIndex = j;
            }
        }

        Animal animal = container.data[maxFunIndex];
        container.data[maxFunIndex] = container.data[size - i - 1];
        container.data[size - i - 1] = animal;
    }
}

