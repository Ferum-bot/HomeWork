//
// Created by ma.d.popov on 08.10.2021.
//

#ifndef NEW_SEMINAR1_ANIMAL_H
#define NEW_SEMINAR1_ANIMAL_H

#pragma once

#include <fstream>
#include <string>

class Animal {
public:

    Animal();

    Animal(std::string name, int32_t weight);

    Animal(const Animal& animal);

    virtual ~Animal();

    virtual Animal& operator = (const Animal& animal);

    virtual Animal* copy();

    std::string getName() const;

    int32_t getWeight() const;

    void setName(const std::string& name) noexcept;

    void setWeight(const int32_t& weight) noexcept;

protected:

    std::string* name;

    int weight;

};


#endif //NEW_SEMINAR1_ANIMAL_H
