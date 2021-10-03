#ifndef TASK1_CONTAINER_H
#define TASK1_CONTAINER_H

#include "../animals/info/Animal.h"

struct Container {
    int size;

    Animal* data;
};

void init(Container& container, int size);

void deInit(Container& container);

void inputFromStream(Container& container, std::ifstream& input);

void randomInput(Container& container);

void outputToStream(Container& container, std::ofstream& output);

void sortContainerData(Container& container);

#endif //TASK1_CONTAINER_H
