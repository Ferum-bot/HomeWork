//
// Created by Матвей Попов on 14.10.2021.
//

#include "RandomInputProvider.h"


size_t RandomInputProvider::readInputSizeFrom(std::istream &input) {
    return RandomUtil::randomInt();
}

Animal * RandomInputProvider::readAnimalFrom(std::istream &input) {
    int32_t type = randomType();
    Animal* animal;
    switch (type) {
        case 1: {
            animal = randomBeast();
            break;
        }
        case 2: {
            animal = randomBird();
            break;
        }
        case 3: {
            animal = randomFish();
            break;
        }
        default: {
            animal = randomFish();
        }
    }
    return animal;
}

int32_t RandomInputProvider::randomType() const {
    return RandomUtil::randomInt() % 3 + 1;
}

Beast * RandomInputProvider::randomBeast() const {
    std::string name = RandomUtil::randomName();
    double weight = RandomUtil::randomInt();
    Beast* beast = new Beast(name, weight);

    int32_t type = RandomUtil::randomInt() % 3 + 1;
    switch (type) {
        case 1: {
            beast->setType(Beast::Predator);
            break;
        }
        case 2: {
            beast->setType(Beast::Herbivores);
            break;
        }
        case 3: {
            beast->setType(Beast::Insectivores);
            break;
        }
        default: {
            beast->setType(Beast::Predator);
        }
    }
    return beast;
}

Bird * RandomInputProvider::randomBird() const {
    std::string name = RandomUtil::randomName();
    double weight = RandomUtil::randomInt();
    Bird* bird = new Bird(name, weight);
    bool isTransferable = RandomUtil::randomInt() % 2;
    bird->setIsTransferable(isTransferable);
    return bird;
}

Fish * RandomInputProvider::randomFish() const {
    std::string name = RandomUtil::randomName();
    double weight = RandomUtil::randomInt();
    Fish* fish = new Fish(name, weight);

    int32_t type = RandomUtil::randomInt() % 3 + 1;
    switch (type) {
        case 1: {
            fish->setLocation(Fish::SEA);
            break;
        }
        case 2: {
            fish->setLocation(Fish::OCEAN);
            break;
        }
        case 3: {
            fish->setLocation(Fish::LAKE);
            break;
        }
        default: {
            fish->setLocation(Fish::LAKE);
            break;
        }
    }
    return fish;
}