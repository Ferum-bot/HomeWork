#ifndef AVS_TASK1_RANDOM_H
#define AVS_TASK1_RANDOM_H

#include <cstdlib>
#include "../animals/info/Animal.h"

int randomSmallInt();

int randomBigInt();

double randomSmallDouble();

Animal::Key randomAnimal();

char* randomName(int length);

#endif //AVS_TASK1_RANDOM_H
