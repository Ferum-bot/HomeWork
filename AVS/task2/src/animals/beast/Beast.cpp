//
// Created by Матвей Попов on 14.10.2021.
//

#include "Beast.h"

Beast::Beast(const std::string &name, const int32_t& weight): Animal(name, weight) {}

Beast::Beast(const Beast &beast): Animal(beast) {
    type = beast.type;
}

Beast::~Beast() noexcept = default;

Beast & Beast::operator=(const Beast &beast) {
    Animal::operator=(beast);
    type = beast.type;
    return *this;
}

Animal* Beast::copy() {
    Beast* currentBeast = dynamic_cast<Beast*>(this);
    Beast* copyBeast = new Beast(*name, weight);
    copyBeast->setType(currentBeast->getType());
    return copyBeast;
}

Beast::Type Beast::getType() const {
    return type;
}

void Beast::setType(Type type) {
    this->type = type;
}