//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_FISH_H
#define TASK2_FISH_H

#include "../animal/Animal.h"

class Fish final: public Animal {
public:

    enum Location { SEA, OCEAN, LAKE };

    Fish(const std::string& name, const int32_t& weight);

    Fish(const Fish& fish);

    ~Fish();

    Fish& operator = (const Fish& fish);

    Location getLocation() const;

    void setLocation(const Location& location);

private:

    Location location;

};


#endif //TASK2_FISH_H
