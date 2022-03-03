package com.ferumbot.mapper.impl.classes;

import ru.hse.homework4.DateFormat;
import ru.hse.homework4.Exported;

import java.time.LocalDateTime;

@Exported
public class InvalidClass1 {

    private int[] children;

    private String firstProperty;

    private String secondProperty;

    private Integer count;

    @DateFormat("FF:H$123-HH")
    private LocalDateTime invalidDateTime;

    public InvalidClass1() {
        children = new int[10];
        firstProperty = "first property";
        secondProperty = "second property";
        count = -15;
        invalidDateTime = LocalDateTime.now();
    }

    public int[] getChildren() {
        return children;
    }

    public void setChildren(int[] children) {
        this.children = children;
    }

    public String getFirstProperty() {
        return firstProperty;
    }

    public void setFirstProperty(String firstProperty) {
        this.firstProperty = firstProperty;
    }

    public String getSecondProperty() {
        return secondProperty;
    }

    public void setSecondProperty(String secondProperty) {
        this.secondProperty = secondProperty;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getInvalidDateTime() {
        return invalidDateTime;
    }

    public void setInvalidDateTime(LocalDateTime invalidDateTime) {
        this.invalidDateTime = invalidDateTime;
    }
}
