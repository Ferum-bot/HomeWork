//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_RANDOMINPUTPROVIDER_H
#define TASK2_RANDOMINPUTPROVIDER_H

#include "../InputProvider.h"
#include "../../../random/RandomUtil.h"

class RandomInputProvider: public InputProvider {
public:

    size_t readInputSizeFrom(const std::istream &input) override;

    Animal * readAnimalFrom(const std::istream &input) override;

private:

    int32_t randomType() const;

    Beast* randomBeast() const;

    Bird* randomBird() const;

    Fish* randomFish() const;
};


#endif //TASK2_RANDOMINPUTPROVIDER_H
