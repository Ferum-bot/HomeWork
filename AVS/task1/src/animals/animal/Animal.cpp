#include "Animal.h"

void inputFromStream(Animal& info, std::ifstream& input) {
    char* name;
    int weight, type_int;
    Animal::Key type;
    input >> type_int >> name >> weight;

    switch (type_int) {
        case 1: {
            type = Animal::Bird;
            inputFromStream(info.bird, input);
            break;
        }
        case 2: {
            type = Animal::Fish;
            inputFromStream(info.fish, input);
            break;
        }
        case 3: {
            type = Animal::Beast;
            inputFromStream(info.beast, input);
            break;
        }
        default: {
            std::cout << "Invalid Animal type! Available only: 1(Bird), 2(Fish), 3(Beast)\n";
            return;
        }
    }

    info.type = type;
    info.weight = weight;
    info.name = name;
}

void randomInput(Animal& info) {
    int type = randomSmallInt() % 3;
    int length = randomSmallInt() % 10 + 3;
    info.weight = randomBigInt();
    info.name = randomName(length);

    switch (type) {
        case 0: {
            info.type = Animal::Bird;
            randomInput(info.bird);
            break;
        }
        case 1: {
            info.type = Animal::Fish;
            randomInput(info.fish);
            break;
        }
        case 2: {
            info.type = Animal::Beast;
            randomInput(info.beast);
            break;
        }
    }
}

void outputToStream(Animal& info, std::ofstream& output) {
    switch (info.type) {
        case Animal::Bird: {
            outputToStream(info.bird, output);
            break;
        }
        case Animal::Beast: {
            outputToStream(info.beast, output);
            break;
        }
        case Animal::Fish: {
            outputToStream(info.fish, output);
            break;
        }
    }
    output << "The name of animal is " << info.name << "\n";
    output << "The weight of animal is " << info.weight << "\n";
    output << "The quotient from name to weight is " << calculateFunction(info) << "\n";
}

double calculateFunction(Animal& info) {
    int sumOfCodes = 0;
    int length = std::strlen(info.name);
    for (int i = 0; i < length; i++) {
        sumOfCodes += *(info.name + i);
    }
    return (double)sumOfCodes / (double) info.weight;
}
