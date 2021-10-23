#ifndef TASK2_INPUTPROVIDER_H
#define TASK2_INPUTPROVIDER_H

#pragma once

#include "../../animals/animal/Animal.h"
#include "../../animals/bird/Bird.h"
#include "../../animals/fish/Fish.h"
#include "../../animals/beast/Beast.h"
#include <iostream>

class InputProvider {
public:

    virtual Animal* readAnimalFrom(std::istream& input) = 0;

    virtual size_t readInputSizeFrom(std::istream& input) = 0;

private:

};

#endif //TASK2_INPUTPROVIDER_H
