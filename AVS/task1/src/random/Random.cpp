#include "Random.h"

int randomSmallInt() {
    return rand() % 1000 + 1;
}

int randomBigInt() {
    return rand() % 1000000 + 1;
}

double randomSmallDouble() {
    return (double)(rand() % 1000000) / 1000 + 0.003;
}

Animal::Key randomAnimal() {
    int randomAlias = rand() % 3;
    switch (randomAlias) {
        case 0: {
            return Animal::Key::Bird;
        }
        case 1: {
            return Animal::Key::Fish;
        }
        case 2: {
            return Animal::Key::Beast;
        }
        default: {
            return Animal::Key::Bird;
        }
    }
}

char* randomName(int length) {
    char* resultName = new char[length];
    for (int i = 0; i < length; i++) {
        char character = 'a' + randomBigInt() % 26;
        *(resultName + i) = character;
    }
    return resultName;
}
