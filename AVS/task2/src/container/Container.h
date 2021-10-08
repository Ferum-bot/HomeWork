//
// Created by ma.d.popov on 08.10.2021.
//

#ifndef NEW_SEMINAR1_CONTAINER_H
#define NEW_SEMINAR1_CONTAINER_H

#include "../animals/animal/Animal.h"

class Container final {
public:

    Container(const int& size);

    Container(const Container& container);

    ~Container();

    void writeToStream(const std::ofstream& stream);

    void readFromStream(const std::ifstream& stream);

    void randomInput();

    void sort();

private:

    int size = 0;

    Animal* data;
};


#endif //NEW_SEMINAR1_CONTAINER_H
