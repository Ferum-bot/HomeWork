//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_OUTPUTPROVIDER_H
#define TASK2_OUTPUTPROVIDER_H

#include "../../animals/animal/Animal.h"
#include "../../animals/bird/Bird.h"
#include "../../animals/fish/Fish.h"
#include "../../animals/beast/Beast.h"
#include <iostream>

class OutputProvider {
public:

    virtual void writeMessageTo(const std::string& message, const std::ostream& output) = 0;

    virtual void writeAnimalTo(const Animal* animal, const std::ostream& output) = 0;

    virtual void invalidParametersList() = 0;

    virtual void invalidFlagAlias() = 0;

    virtual void processStarted() = 0;

    virtual void containerFilled() = 0;

    virtual void inputPrinted() = 0;

    virtual void containerSorted() = 0;

    virtual void resultPrinted() = 0;

    virtual void processFinished() = 0;

private:


};

#endif //TASK2_OUTPUTPROVIDER_H
