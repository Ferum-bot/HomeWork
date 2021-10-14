//
// Created by ma.d.popov on 08.10.2021.
//

#include "Container.h"

Container::Container(const size_t& size) {
    this->size = size;
    this->data = new Animal[size];
}

Container::~Container() {
    deleteData();
}

Container::Container(const Container &container) {
    if (this == &container) {
        return;
    }
    deleteData();
    size = container.size;
    data = new Animal[size];
    for (size_t i = 0; i < size; i++) {
        (data + i) = container[i]->copy();
    }
}

Container & Container::operator=(const Container &container) noexcept {
    if (this == &container) {
        return *this;
    }
    deleteData();
    size = container.size;
    data = new Animal[size];
    for (size_t i = 0; i < size; i++) {
        (data + i) = container[i]->copy();
    }
}

Animal* Container::operator[](const size_t &pos) throw() {
    if (pos >= size) {
        throw std::out_of_range("To big position to access data");
    }
    return (data + pos);
}

size_t Container::getSize() const {
    return size;
}

void Container::deleteData() noexcept {
    delete[] data;
    size = 0;
}