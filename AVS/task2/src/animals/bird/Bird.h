//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_BIRD_H
#define TASK2_BIRD_H

#include "../animal/Animal.h"

class Bird final: public Animal {
public:

    Bird(const std::string& name, const int32_t& weight);

    Bird(const Bird& bird);

    ~Bird();

    Bird& operator = (const Bird& bird);

    Animal * copy() override;

    void setIsTransferable(bool isTransferable);

    bool getIsTransferable() const;

private:

    bool* isTransferable;

};


#endif //TASK2_BIRD_H
