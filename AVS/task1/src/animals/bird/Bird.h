#ifndef TASK1_BIRD_H
#define TASK1_BIRD_H

#include <iostream>
#include <fstream>

struct Bird {
    bool isTransferable;
};

void inputFromStream(Bird& bird, std::fstream& input);

void randomInput(Bird& bird);

void outputToStream(Bird& bird, std::ofstream& output);

#endif //TASK1_BIRD_H
