/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

class Car {
private:
    
    int number_of_seats;
    int number_of_doors;
    
    int engine_power;
    
    bool gps;
    bool trip_computer;
    
public:
    
    Car() {
        this->number_of_doors = 0;
        this->number_of_seats = 0;
        this->engine_power = 0;
        this->gps = false;
        this->trip_computer = false;
    }
    
    Car(const Car& curr) = default; 
    
    ~Car() = default;
    
    friend class Builder;
};

class Builder {
private:
    
    Car car;
    
public:
    
    void reset(Car curr) {
        this->car = curr;
        return;
    }
    
    void setSeats(int value) {
        this->car.number_of_seats = value;
        return;
    }
    
    void setDoors(int value) {
        this->car.number_of_doors =  value;
        return;
    }
    
    void setEnginePower(int value) {
        this->car.engine_power = value;
        return;
    }
    
    void setGPS(bool value) {
        this->car.gps = value;
        return;
    }
    
    void setTripComputer(bool value) {
        this->car.trip_computer = value;
        return;
    }
    
    Car getResult() {
        return this->car;
    }
};

int main() {
    
    
    return 0;
}
