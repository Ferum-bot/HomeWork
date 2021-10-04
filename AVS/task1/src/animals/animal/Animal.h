#ifndef TASK1_ANIMAL_H
#define TASK1_ANIMAL_H

#include <iostream>
#include "../beast/Beast.h"
#include "../bird/Bird.h"
#include "../fish/Fish.h"
#include "../../random/Random.h"

struct Animal {

    enum Key { Bird, Fish, Beast };

    union {
        Bird bird;
        Fish fish;
        Beast beast;
    };

    char* name;
    int weight;
    Key type;
};

void inputFromStream(Animal& info, std::ifstream& input);

void randomInput(Animal& info);

void outputToStream(Animal& info, std::ofstream& output);

double calculateFunction(Animal& info);

#endif //TASK1_ANIMAL_H
