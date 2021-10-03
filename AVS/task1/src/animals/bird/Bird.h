#ifndef TASK1_BIRD_H
#define TASK1_BIRD_H

#include <iostream>
#include <fstream>
#include "../../random/Random.h"

struct Bird {
    bool isTransferable;
};

void inputFromStream(Bird& bird, std::ifstream& input);

void randomInput(Bird& bird);

void outputToStream(Bird& bird, std::ofstream& output);

#endif //TASK1_BIRD_H
