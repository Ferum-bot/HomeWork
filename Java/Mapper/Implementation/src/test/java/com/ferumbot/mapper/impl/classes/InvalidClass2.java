package com.ferumbot.mapper.impl.classes;

import ru.hse.homework4.DateFormat;
import ru.hse.homework4.Exported;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Exported
public class InvalidClass2 {

    @DateFormat("abcd")
    private LocalDate date;

    @DateFormat("HH:yyyy")
    private LocalTime time;

    @DateFormat("test")
    private LocalDateTime dateTime;

    public InvalidClass2() {
        date = LocalDate.now();
        time = LocalTime.now();
        dateTime = LocalDateTime.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
