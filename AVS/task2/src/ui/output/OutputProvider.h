//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_OUTPUTPROVIDER_H
#define TASK2_OUTPUTPROVIDER_H

#include "../../animals/animal/Animal.h"

class OutputProvider {
public:

    virtual void writeMessageTo(const std::string& message, const std::ostream& output) = 0;

    virtual void writeAnimalTo(const Animal* animal, const std::ostream& output) = 0;

private:


};

#endif //TASK2_OUTPUTPROVIDER_H
