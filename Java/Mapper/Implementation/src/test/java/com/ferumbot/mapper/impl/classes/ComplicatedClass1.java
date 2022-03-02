package com.ferumbot.mapper.impl.classes;

import ru.hse.homework4.DateFormat;
import ru.hse.homework4.Exported;
import ru.hse.homework4.Ignored;
import ru.hse.homework4.PropertyName;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Exported
public class ComplicatedClass1 {

    private String name = "name";

    private String surname = "surname";

    @Ignored
    private String lastName = "lastName";

    private long count = 124L;

    @PropertyName("last_price")
    private Double price = 123.3;

    private LocalDateTime currentDate = LocalDateTime.now();

    @DateFormat("dd-MM-yyyy")
    private LocalDate lastDate = LocalDate.now();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }
}
