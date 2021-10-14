//
// Created by Матвей Попов on 14.10.2021.
//

#include "FileInputProvider.h"

size_t FileInputProvider::readInputSizeFrom(const std::istream &input) {
    size_t result;
    input >> result;
    return result;
}

Animal * FileInputProvider::readAnimalFrom(const std::istream &input) {
    int32_t animalType;
    input >> animalType;
    Animal* animal;
    switch (animalType) {
        case 1: {
            animal = readBeast(input);
            break;
        }
        case 2: {
            animal = readBird(input);
            break;
        }
        case 3: {
            animal = readFish(input);
            break;
        }
        default: {
            break;
        }
    }
    return animal;
}

Beast * FileInputProvider::readBeast(const std::istream &input) const {
    std::string name;
    int32_t weight;
    cin >> name >> weight;
    Beast* beast = new Beast(name, weight);
    std::string type;
    cin >> type;
    if (type ==  "Predator") {
        beast->setType(Beast::Predator);
        return beast;
    }
    if (type == "Herbivores") {
        beast->setType(Beast::Herbivores);
        return beast;
    }
    if (type ==  "Insectivores") {
        beast->setType(Beast::Insectivores);
        return beast;
    }
    beast->setType(Beast::Predator);
    return beast;
}

Bird * FileInputProvider::readBird(const std::istream &input) const {
    std::string name;
    int32_t weight;
    cin >> name >> weight;
    Bird* bird = new Bird(name, weight);
    bool isTransferable;
    input >> isTransferable;
    bird->setIsTransferable(isTransferable);
    return bird;
}

Fish * FileInputProvider::readFish(const std::istream &input) const {
    std::string name;
    int32_t weight;
    cin >> name >> weight;
    Fish* fish = new Fish(name, weight);
    std::string location;
    input >> location;
    if (location == "Sea") {
        fish->setLocation(Fish::SEA);
        return fish;
    }
    if (location == "Ocean") {
        fish->setLocation(Fish::OCEAN);
        return fish;
    }
    if (location == "Lake") {
        fish->setLocation(Fish::LAKE);
        return fish;
    }
    fish->setLocation(Fish::SEA);
    return fish;
}
