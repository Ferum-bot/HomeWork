//
// Created by ma.d.popov on 08.10.2021.
//

#include "Animal.h"

Animal::Animal(std::string name, int weight) {
    this->name = &name;
    this->weight = weight;
}

Animal::Animal(const Animal &animal) {
    this->name = new std::string(*animal.name);
    this->weight = int32_t(animal.weight);
}

Animal& Animal::operator=(const Animal &animal) {
    this->name = new std::string(*animal.name);
    this->weight = int32_t(animal.weight);
    return *this;
}

Animal::~Animal() {
    delete name;
}

std::string Animal::getName() const {
    return *name;
}

int32_t Animal::getWeight() const {
    return weight;
}

Animal *Animal::copy() {
    return this;
}
