//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_TASKCONTROLLER_H
#define TASK2_TASKCONTROLLER_H

#include "../ui/input/InputProvider.h"
#include "../ui/output/OutputProvider.h"

class TaskController final {
public:

    TaskController(int argc, char* argv[]);

    void startWork() noexcept;

private:

    int argumentsCount;

    char** argumentsAliases;

    InputProvider inputProvider;
    OutputProvider outputProvider;
};


#endif //TASK2_TASKCONTROLLER_H
