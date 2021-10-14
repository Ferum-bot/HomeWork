//
// Created by Матвей Попов on 14.10.2021.
//

#include "Bird.h"

Bird::Bird(const std::string &name, const int32_t &weight): Animal(name, weight) {}

Bird::Bird(const Bird &bird): Animal(bird) {
    isTransferable = new bool(bird.isTransferable);
}

Bird::~Bird() noexcept {
    delete isTransferable;
}

Bird& Bird::operator=(const Bird &bird) {
    Animal::operator=(bird);
    isTransferable = new bool (bird.isTransferable);
    return *this;
}

Animal* Animal::copy() {
    Bird* currentBird = dynamic_cast<Bird*>(this);
    Bird* copyBird = new Bird(*name, *weight);
    copyBird->setIsTransferable(currentBird->getIsTransferable());
    return copyBird;
}

bool Bird::getIsTransferable() const {
    return *isTransferable;
}

void Bird::setIsTransferable(const bool& isTransferable) {
    this->isTransferable = &isTransferable
}
