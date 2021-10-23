//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_STRAIGHTSELECTORSORTER_H
#define TASK2_STRAIGHTSELECTORSORTER_H

#pragma once

#include "../ContainerSorter.h"

class StraightSelectorSorter: public ContainerSorter {

    Container * sortContainer(
        const Container *container, std::function<double (const Animal)> comparator
    ) override;

};


#endif //TASK2_STRAIGHTSELECTORSORTER_H
