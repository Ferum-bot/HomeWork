//
// Created by Матвей Попов on 14.10.2021.
//

#include "Fish.h"

Fish::Fish(const std::string &name, const int32_t &weight): Animal(name, weight) {  }

Fish::Fish(const Fish &fish): Animal(fish) {
    location = fish.location;
}

Fish::~Fish() noexcept {

}

Fish& Fish::operator=(const Fish &fish) {
    Animal::operator=(fish);
    location = fish.location;
    return *this;
}

Animal * Fish::copy() {
    Fish *currentFish = dynamic_cast<Fish *>(this);
    Fish *copyFish = new Fish(*name, weight);
    copyFish->setLocation(currentFish->getLocation());
    return copyFish;
}

Fish::Location Fish::getLocation() const {
    return location;
}

void Fish::setLocation(const Location &location) {
    this->location = location;
}
