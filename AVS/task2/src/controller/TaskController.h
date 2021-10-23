//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_TASKCONTROLLER_H
#define TASK2_TASKCONTROLLER_H

#pragma once

#include "../ui/input/random/RandomInputProvider.h"
#include "../ui/input/file/FileInputProvider.h"
#include "../ui/output/file/FileOutputProvider.h"
#include "../sorter/impl/StraightSelectorSorter.h"
#include <cstdlib>
#include <cstring>
#include <ctime>

class TaskController final {
public:

    TaskController(int argc, char* argv[]);

    void startWork() noexcept;

private:

    int argumentsCount;

    char** argumentsAliases;

    InputProvider* inputProvider;
    OutputProvider* outputProvider;

    Container* container;

    ContainerSorter* sorter;

    static double sortFunction(const Animal animal) noexcept;

    void configureController() noexcept;

    void fillContainerFromFile(std::istream& input) noexcept;

    void fillRandomContainer(std::istream& input) noexcept;

    void fillContainer(std::istream& input) noexcept;

    void processContainer() const;

    void sortContainer();

    void showContainer() noexcept;
};


#endif //TASK2_TASKCONTROLLER_H
