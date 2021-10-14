//
// Created by ma.d.popov on 08.10.2021.
//

#include "Animal.h"

Animal::Animal(const std::string &name, const int32_t *weight) {
    this->name = &name;
    this->weight = &weight;
}

Animal::Animal(const Animal &animal) {
    this->name = new std::string(animal.name);
    this->weight = new int32_t(animal.weight);
}

Animal& Animal::operator=(const Animal &animal) {
    this->name = new std::string(animal.name);
    this->weight = new int32_t(animal.weight);
    return *this;
}

Animal::~Animal() {
    delete weight;
    delete name;
}

std::string Animal::getName() const {
    return *name;
}

int32_t Animal::getWeight() const {
    return *weight;
}
