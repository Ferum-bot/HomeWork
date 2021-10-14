//
// Created by ma.d.popov on 08.10.2021.
//

#ifndef NEW_SEMINAR1_ANIMAL_H
#define NEW_SEMINAR1_ANIMAL_H

#include <fstream>
#include <string>

class Animal {
public:

    Animal();

    Animal(const std::string& name, const int32_t* weight);

    Animal(const Animal& animal);

    virtual ~Animal();

    virtual Animal& operator = (const Animal& animal);

    virtual Animal* copy() = 0;

    std::string getName() const;

    int32_t getWeight() const;

    void setName(const std::string& name) noexcept;

    void setWeight(const int32_t& weight) noexcept;

protected:

    std::string* name;

    int32_t* weight;

};


#endif //NEW_SEMINAR1_ANIMAL_H
