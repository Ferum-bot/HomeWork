#ifndef TASK2_INPUTPROVIDER_H
#define TASK2_INPUTPROVIDER_H

#include "../../animals/animal/Animal.h"
#include <iostream>

class InputProvider {
public:

    virtual Animal* readAnimalFrom(const std::istream& input) = 0;

    virtual size_t readInputSizeFrom(const std::istream& input) = 0;

private:

};

#endif //TASK2_INPUTPROVIDER_H
