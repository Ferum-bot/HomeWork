#include "Bird.h"

void inputFromStream(Bird& bird, std::ifstream& input) {
    bool isTransferable;
    input >> isTransferable;
    bird.isTransferable = isTransferable;
}

void randomInput(Bird& bird) {
    bool isTransferable = randomSmallInt() % 2;
    bird.isTransferable = isTransferable;
}

void outputToStream(Bird& bird, std::ofstream& output) {
    output << "This is the Bird that ";
    if (bird.isTransferable) {
        output << "is transferable\n";
    } else {
        output << "is not transferable\n";
    }
}

