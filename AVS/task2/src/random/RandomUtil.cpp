//
// Created by Матвей Попов on 14.10.2021.
//

#include "RandomUtil.h"

double RandomUtil::randomDouble() {
    return rand() % 1000 + 1;
}

int RandomUtil::randomInt() {
    return rand() % 1000 + 1;
}

std::string RandomUtil::randomName() {
    int32_t length = randomInt() % 10 + 1;
    std::string result;
    for (int32_t i = 0; i < length; i++) {
        char randomCharacter = 'a' + randomInt() % 25;
        result += randomCharacter;
    }
    return result;
}
