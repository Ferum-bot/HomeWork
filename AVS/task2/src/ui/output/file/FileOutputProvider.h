//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_FILEOUTPUTPROVIDER_H
#define TASK2_FILEOUTPUTPROVIDER_H

#pragma once

#include "../OutputProvider.h"

class FileOutputProvider: public OutputProvider {
public:

    void writeAnimalTo(Animal* animal,std::ostream &output) override;

    void writeMessageTo(const std::string &message, std::ostream &output) override;

    void invalidParametersList() override;

    void invalidFlagAlias() override;

    void processStarted() override;

    void containerFilled() override;

    void inputPrinted() override;

    void containerSorted() override;

    void resultPrinted() override;

    void processFinished() override;

private:

    void writeBeast(const Beast* beast, std::ostream& output);

    void writeBird(const Bird* bird, std::ostream& output);

    void writeFish(const Fish* fish, std::ostream& output);
};


#endif //TASK2_FILEOUTPUTPROVIDER_H
