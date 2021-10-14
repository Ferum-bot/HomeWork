//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_BEAST_H
#define TASK2_BEAST_H

#include "../animal/Animal.h"

class Beast final: public Animal {
public:

    enum Type { Predator, Herbivores, Insectivores };

    Beast(const std::string& name, const int32_t& weight);

    Beast(const Beast& beast);

    ~Beast();

    Beast& operator = (const Beast& beast);

    Type getType() const;

    void setType(Type type);

private:

    Type type;

};


#endif //TASK2_BEAST_H
