//
// Created by ma.d.popov on 08.10.2021.
//

#ifndef NEW_SEMINAR1_CONTAINER_H
#define NEW_SEMINAR1_CONTAINER_H

#include "../animals/animal/Animal.h"

class Container final {
public:

    Container(const size_t& size) noexcept;

    Container(const Container& container) noexcept;

    ~Container() noexcept;

    Container& operator = (const Container& container) noexcept;

    Animal& operator [] (const size_t& pos) throw;

    size_t getSize() const;

    Container* copy() const;

private:

    size_t size = 0;

    Animal* data;

    void deleteData() noexcept;
};


#endif //NEW_SEMINAR1_CONTAINER_H
