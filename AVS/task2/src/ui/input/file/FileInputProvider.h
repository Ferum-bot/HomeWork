//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_FILEINPUTPROVIDER_H
#define TASK2_FILEINPUTPROVIDER_H

#pragma once

#include "../InputProvider.h"

class FileInputProvider: public InputProvider {
public:

    Animal * readAnimalFrom(std::istream &input) override;

    size_t readInputSizeFrom(std::istream &input) override;

private:

    Bird* readBird(std::istream& input) const;

    Fish* readFish(std::istream& input) const;

    Beast* readBeast(std::istream& input) const;
};


#endif //TASK2_FILEINPUTPROVIDER_H
