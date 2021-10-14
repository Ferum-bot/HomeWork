//
// Created by Матвей Попов on 14.10.2021.
//

#include "StraightSelectorSorter.h"

Container * StraightSelectorSorter::sortContainer(
    const Container* container, std::function<double (const Animal *)> comparator
) {
    const size_t size = container->getSize();
    const Container* resultContainer = container->copy();

    for (size_t i = 0; i < size; i++) {
        const Animal* firstAnimal = (*resultContainer)[i];
        size_t maxIndex = i;
        double maxValue = comparator(firstAnimal);

        for (size_t j = 0; j < size - i; j++) {
            const Animal* secondAnimal = (*resultContainer)[j];
            const double currentValue = comparator(secondAnimal);
            if (currentValue > firstValue) {
                maxIndex = j;
                maxValue = currentValue;
            }
        }

        const Animal* currentAnimal = (*resultContainer)[maxIndex];
        (*resultContainer)[maxIndex] = (*resultContainer)[size - i - 1];
        (*resultContainer)[size - i - 1] = currentAnimal;
    }

    return resultContainer;
}