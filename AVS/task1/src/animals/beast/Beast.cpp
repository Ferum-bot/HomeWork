#include "Beast.h"

void inputFromStream(Beast& beast, std::ifstream& input) {
    char* beastType;
    input >> beastType;

    if (std::strcmp(beastType, "Predator") == 0) {
        beast.type = Beast::Type::Predator;
        return;
    }
    if (std::strcmp(beastType, "Herbivores") == 0) {
        beast.type = Beast::Type::Herbivores;
        return;
    }
    if (std::strcmp(beastType, "Insectivores") == 0) {
        beast.type = Beast::Type::Herbivores;
        return;
    }
    std::cout << "Invalid beast type! Available only: Predator, Herbivores, Insectivores \n";
    std::cout << "Setting to default: Predator \n";
    beast.type = Beast::Type::Predator;
}

void randomInput(Beast& beast) {
    int randomType = randomSmallInt() % 3;
    switch (randomType) {
        case 0: {
            beast.type = Beast::Type::Predator;
            break;
        }
        case 1: {
            beast.type = Beast::Type::Herbivores;
            break;
        }
        case 2: {
            beast.type = Beast::Type::Insectivores;
            break;
        }
        default: {
            beast.type = Beast::Type::Predator;
        }
    }
}

void outputToStream(Beast& beast, std::ofstream& output) {
    output << "This is the Beast with type: ";
    switch (beast.type) {
        case Beast::Predator: {
            output << "Predator\n";
            break;
        }
        case Beast::Insectivores: {
            output << "Insectivores\n";
            break;
        }
        case Beast::Herbivores: {
            output << "Herbivores\n";
            break;
        }
    }
}

