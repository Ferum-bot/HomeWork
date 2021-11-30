#ifndef TASK1_BEAST_H
#define TASK1_BEAST_H

#include <iostream>
#include <fstream>
#include "../../random/Random.h"

struct Beast {

    enum Type { Predator, Herbivores, Insectivores };

    Type type;
};

void inputFromStream(Beast& beast, std::ifstream& input);

void randomInput(Beast& beast);

void outputToStream(Beast& beast, std::ofstream& output);

#endif //TASK1_BEAST_H
