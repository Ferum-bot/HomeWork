#include <iostream>

#include "src/controller/TaskController.h"

int main(int argc, char* argv[]) {
    TaskController* controller = new TaskController(argc, argv);

    controller->startWork();

    return 0;
}
