#include "Random.h"

int randomSmallInt() {
    return rand() % 1000 + 1;
}

int randomBigInt() {
    return rand() % 1000000 + 1;
}

char* randomName(int length) {
    char* resultName = new char[length];
    for (int i = 0; i < length; i++) {
        char character = 'a' + randomBigInt() % 26;
        *(resultName + i) = character;
    }
    return resultName;
}
