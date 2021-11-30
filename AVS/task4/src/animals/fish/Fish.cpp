#include "Fish.h"

void inputFromStream(Fish& fish, std::ifstream& input) {
    char* location = new char[64];
    input >> location;

    if (strcmp(location, "Sea") == 0) {
        fish.location = Fish::Sea;
        delete[] location;
        return;
    }
    if (strcmp(location, "Ocean") == 0) {
        fish.location = Fish::Ocean;
        delete[] location;
        return;
    }
    if (strcmp(location, "Lake") == 0) {
        fish.location = Fish::Lake;
        delete[] location;
        return;
    }
    std::cout << "Invalid Fish location! Available only: Sea, Ocean, Lake \n";
    std::cout << "Setting to default: Sea \n";
    fish.location = Fish::Sea;
    delete[] location;
}

void randomInput(Fish& fish) {
    int randomType = randomSmallInt() % 3;
    switch (randomType) {
        case 0: {
            fish.location = Fish::Sea;
            break;
        }
        case 1: {
            fish.location = Fish::Ocean;
            break;
        }
        case 2: {
            fish.location = Fish::Lake;
            break;
        }
        default: {
            fish.location = Fish::Sea;
        }
    }
}

void outputToStream(Fish& fish, std::ofstream& output) {
    output << "This is fish with location: ";
    switch (fish.location) {
        case Fish::Sea: {
            output << "Sea\n";
            break;
        }
        case Fish::Ocean: {
            output << "Ocean\n";
            break;
        }
        case Fish::Lake: {
            output << "Lake\n";
            break;
        }
    }
}
