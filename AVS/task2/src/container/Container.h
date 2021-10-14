//
// Created by ma.d.popov on 08.10.2021.
//

#ifndef NEW_SEMINAR1_CONTAINER_H
#define NEW_SEMINAR1_CONTAINER_H

#include "../animals/animal/Animal.h"

class Container final {
public:

    Container(const int& size) noexcept;

    Container(const Container& container) noexcept;

    ~Container() noexcept;

    Container& operator = (const Container& container) noexcept;

    void sort() noexcept;

    size_t getSize() const;

    Animal& operator [] (const size_t& pos) throw;

private:

    size_t size = 0;

    Animal* data;
};


#endif //NEW_SEMINAR1_CONTAINER_H
