#include <cstdint>
#include <iostream>
#include <string>

class AbstructTable {
protected:
    
    std::string name = "default";
    
    uint32_t index = 0;

    uint16_t color = 0;
    uint16_t size = 10;
    
public:

    virtual ~AbstructTable() {};

    virtual std::string getName() const = 0;
    virtual uint32_t getIndex() const = 0;
    virtual uint16_t getColor() const = 0;
    virtual uint16_t getSize() const = 0;

    virtual std::string getSpecialFunction() const = 0;
};

class ModernTable : public AbstructTable {
public:

    std::string getName() const override {
        return this->name;
    }

    uint32_t getIndex() const override {
        return this->index;
    }

    uint16_t getColor() const override {
        return this->color;
    }

    uint16_t getSize() const override {
        return this->size;
    }

    std::string getSpecialFunction() const override {
        return this->specialColor;
    }

    void setName(const std::string& name) {
        this->name = name;
    }

    void setIndex(const uint32_t& index) {
        this->index = index;
    }

    //other SETs ...

    void setNumberOfLegs(const uint8_t& number) {
        this->numberOfLegs = number;
    }

    void setSpecialColor(const std::string& color) {
        this->specialColor = color;
    }

private:

    uint8_t numberOfLegs = 4;
    
    std::string specialColor = "ffffff";

};


class OldTable : public AbstructTable {
    public:

    std::string getName() const override {
        return this->name;
    }

    uint32_t getIndex() const override {
        return this->index;
    }

    uint16_t getColor() const override {
        return this->color;
    }

    uint16_t getSize() const override {
        return this->size;
    }

    std::string getSpecialFunction() const override {
        return this->specialWord;
    }

    void setName(const std::string& name) {
        this->name = name;
    }

    void setIndex(const uint32_t& index) {
        this->index = index;
    }

    //other SETs ...

    void setNumberOfLegs(const uint8_t& number) {
        this->numberOfLegs = number;
    }

    void setSpecialColor(const std::string& color) {
        this->specialColor = color;
    }

private:

    uint8_t numberOfLegs = 4;
    
    std::string specialWord = "default";

};

signed main() {
    return 0;
}