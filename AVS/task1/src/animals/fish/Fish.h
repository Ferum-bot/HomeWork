#ifndef TASK1_FISH_H
#define TASK1_FISH_H

#include <iostream>
#include <fstream>

struct Fish {

    enum Location { Sea, Ocean, Lake };

    Location location;
};

void inputFromStream(Fish& fish, std::fstream& input);

void randomInput(Fish& fish);

void outputToStream(Fish& fish, std::ofstream& output);

#endif //TASK1_FISH_H