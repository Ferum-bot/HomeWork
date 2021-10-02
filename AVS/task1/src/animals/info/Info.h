#ifndef TASK1_INFO_H
#define TASK1_INFO_H

#include <iostream>
#include "../beast/Beast.h"
#include "../bird/Bird.h"
#include "../fish/Fish.h"

struct Info {

    enum Key { Bird, Fish, Beast };

    union {
        Bird bird;
        Fish fish;
        Beast beast;
    };

    char name[];
    int weight;
    Key type;
};

void inputFromStream(Info& info, std::ifstream& input);

void randomInput(Info& info);

void outputToStream(Info& info, std::ofstream& output);

double calculateFunction(Info& info);

#endif //TASK1_INFO_H
