//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_FILEINPUTPROVIDER_H
#define TASK2_FILEINPUTPROVIDER_H

#include "../InputProvider.h"

class FileInputProvider: public InputProvider {
public:

    Animal * readAnimalFrom(const std::istream &input) override;

    size_t readInputSizeFrom(const std::istream &input) override;

private:

    Bird* readBird(const std::istream& input) const;

    Fish* readFish(const std::istream& input) const;

    Beast* readBeast(const std::istream& input) const;
};


#endif //TASK2_FILEINPUTPROVIDER_H
