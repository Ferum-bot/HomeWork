//
// Created by Матвей Попов on 14.10.2021.
//

#include "TaskController.h"

TaskController::TaskController(int argc, char **argv) {
    argumentsCount = argc;
    argumentsAliases = argv;
    outputProvider = new FileOutputProvider();
}

void TaskController::startWork() noexcept {
    outputProvider->processStarted();
    configureController();
    outputProvider->containerFilled();
    processContainer();
    outputProvider->inputPrinted();
    sortContainer();
    outputProvider->containerSorted();
    showContainer();
    outputProvider->resultPrinted();
    outputProvider->processFinished();
}

void TaskController::configureController() noexcept {
    if (argumentsCount != 5) {
        outputProvider->invalidParametersList();
        exit(1);
    }

    int size = 0;

    if (std::strcmp(argumentsAliases[1], "-f") == 0) {
        std::ifstream inputStream(argumentsAliases[2]);
        fillContainer(inputStream);

        return;
    }

    if (std::strcmp(argumentsAliases[1], "-g") == 0) {
        std::ifstream input(argumentsAliases[2]);
        fillRandomContainer(input);

        return;
    }

    outputProvider->invalidFlagAlias();
    exit(2);
}

void TaskController::fillContainerFromFile(std::istream& input) noexcept {
    inputProvider = new FileInputProvider();
    fillContainer(input);
}

void TaskController::fillRandomContainer(std::istream &input) noexcept {
    srand(time(nullptr));
    inputProvider = new RandomInputProvider();
    fillContainer(input);
}

void TaskController::fillContainer(std::istream &input) noexcept {
    size_t size;
    input >> size;
    container = new Container(size);
    for (size_t i = 0; i < size; i++) {
        (*container)[i] = *(inputProvider->readAnimalFrom(input));
    }
}

void TaskController::processContainer() const {
    std::ofstream output(argumentsAliases[3]);
    const size_t size = container->getSize();
    outputProvider->writeMessageTo(&"The The size of Container = " [size], output);
    for (size_t i = 0; i < size; i++) {
        outputProvider->writeAnimalTo(&(*container)[i], output);
    }
}

void TaskController::sortContainer() {
    sorter = new StraightSelectorSorter();
    const size_t size = container->getSize();
    container = sorter->sortContainer(container, &sortFunction);
}

void TaskController::showContainer() noexcept {
    std::ofstream output(argumentsAliases[4]);
    const size_t size = container->getSize();
    outputProvider->writeMessageTo(&"The size of sorted container = " [ size], output);
    for (size_t i = 0; i < size; i++) {
        outputProvider->writeAnimalTo(&(*container)[i], output);
    }
}

double TaskController::sortFunction(const Animal animal) noexcept {
    int sumOfCodes = 0;
    int length = animal.getName().length();
    for (int i = 0; i < length; i++) {
        sumOfCodes += animal.getName().at(i);
    }
    return (double) sumOfCodes / (double) animal.getWeight();
}
