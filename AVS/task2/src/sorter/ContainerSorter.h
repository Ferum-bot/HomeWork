//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_CONTAINERSORTER_H
#define TASK2_CONTAINERSORTER_H

#pragma once

#include "../container/Container.h"

class ContainerSorter {
public:

    virtual Container* sortContainer(const Container* container) = 0;

};

#endif //TASK2_CONTAINERSORTER_H
