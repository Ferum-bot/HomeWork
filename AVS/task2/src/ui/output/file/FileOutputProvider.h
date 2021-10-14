//
// Created by Матвей Попов on 14.10.2021.
//

#ifndef TASK2_FILEOUTPUTPROVIDER_H
#define TASK2_FILEOUTPUTPROVIDER_H

#include "../OutputProvider.h"

class FileOutputProvider: public OutputProvider {
public:

    void writeAnimalTo(const Animal *animal, const std::ostream &output) override;

    void writeMessageTo(const std::string &message, const std::ostream &output) override;

    void invalidParametersList() override;

    void invalidFlagAlias() override;

    void processStarted() override;

    void containerFilled() override;

    void inputPrinted() override;

    void containerSorted() override;

    void resultPrinted() override;

    void processFinished() override;

private:

    void writeBeast(const Beast* beast, const std::ostream& output);

    void writeBird(const Bird* bird, const std::ostream& output);

    void writeFish(const Fish* fish, const std::ostream& output);
};


#endif //TASK2_FILEOUTPUTPROVIDER_H
