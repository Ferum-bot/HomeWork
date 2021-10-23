//
// Created by Матвей Попов on 14.10.2021.
//

#include "FileOutputProvider.h"

void FileOutputProvider::writeMessageTo(const std::string &message, std::ostream &output) {
    output << message << std::endl;
}

void FileOutputProvider::writeAnimalTo(Animal* animal, std::ostream &output) {
    Beast* beast = dynamic_cast<Beast*>(animal);
    Bird* bird = dynamic_cast<Bird*>(animal);
    Fish* fish = dynamic_cast<Fish*>(animal);

    if (beast != nullptr) {
        writeBeast(beast, output);
        return;
    }
    if (bird != nullptr) {
        writeBird(bird, output);
        return;
    }
    if (fish != nullptr) {
        writeFish(fish, output);
        return;
    }
}

void FileOutputProvider::invalidFlagAlias() {
    std::cout << "Invalid input flag alias!\n";
    std::cout << "Expected:\n";
    std::cout << "-f or -g";
}

void FileOutputProvider::processFinished() {
    std::cout << "Process finished.\n";
}

void FileOutputProvider::resultPrinted() {
    std::cout << "Result printed.\n";
}

void FileOutputProvider::containerSorted() {
    std::cout << "Container sorted.\n";
}

void FileOutputProvider::inputPrinted() {
    std::cout << "Input printed.\n";
}

void FileOutputProvider::containerFilled() {
    std::cout << "Container filled.\n";
}

void FileOutputProvider::invalidParametersList() {
    std::cout << "Invalid input parameters!\n";
    std::cout << "Expected:\n";
    std::cout << "-f <input_file> <out_file_1> <out_file_2> \n";
    std::cout << "Or: \n";
    std::cout << "-g <intput_file> <out_file_1> <out_file_2>";
}

void FileOutputProvider::processStarted() {
    std::cout << "Process started.\n";
}

void FileOutputProvider::writeBeast(const Beast *beast, std::ostream &output) {
    output << "This is the Beast: ";
    output << "Name: " << beast->getName() << " ";
    output << "Weight: " << beast->getWeight() << " ";
    output << "Type: ";
    switch (beast->getType()) {
        case Beast::Predator: {
            output << "Predator" << std::endl;
            break;
        }
        case Beast::Herbivores: {
            output << "Herbivores" << std::endl;
            break;
        }
        case Beast::Insectivores: {
            output << "Insectivores" << std::endl;
            break;
        }
    }
}

void FileOutputProvider::writeBird(const Bird *bird, std::ostream &output) {
    output << "This is the Bird: ";
    output << "Name: " << bird->getName() << " ";
    output << "Weight: " << bird->getWeight() << " ";
    output << "Is transferable: " << bird->getIsTransferable() << std::endl;
}

void FileOutputProvider::writeFish(const Fish *fish, std::ostream &output) {
    output << "This is the Fish: ";
    output << "Name: " << fish->getName() << " ";
    output << "Weight: " << fish->getWeight() << " ";
    output << "Location: ";
    switch (fish->getLocation()) {
        case Fish::SEA: {
            output << "Sea" << std::endl;
            break;
        }
        case Fish::OCEAN: {
            output << "Ocean" << std::endl;
            break;
        }
        case Fish::LAKE: {
            output << "Lake" << std::endl;
            break;
        }
    }
}