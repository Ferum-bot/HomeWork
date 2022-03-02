package com.ferumbot.mapper.impl.classes;

import ru.hse.homework4.DateFormat;
import ru.hse.homework4.Exported;
import ru.hse.homework4.Ignored;
import ru.hse.homework4.PropertyName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Exported
public class ComplicatedClass3 {

    private Integer secondField = 5;

    @PropertyName("is_false")
    private Boolean isTrue = false;

    @PropertyName("second_classes")
    private List<ComplicatedClass1> firstClasses = new ArrayList<>();

    private ComplicatedClass2 innerClass;

    @Ignored
    private ComplicatedClass2 ignoredClass;

    @DateFormat(value = "dd-MM-yyyy")
    private LocalDateTime createdDate;

    private ComplicatedClass1 nullClass;

    public ComplicatedClass3() {
        firstClasses.add(new ComplicatedClass1());
        firstClasses.add(new ComplicatedClass1());

        innerClass = new ComplicatedClass2();
        ignoredClass = new ComplicatedClass2();
        createdDate = LocalDateTime.now();
        nullClass = null;
    }

    public Integer getSecondField() {
        return secondField;
    }

    public void setSecondField(Integer secondField) {
        this.secondField = secondField;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public List<ComplicatedClass1> getFirstClasses() {
        return firstClasses;
    }

    public void setFirstClasses(List<ComplicatedClass1> firstClasses) {
        this.firstClasses = firstClasses;
    }

    public ComplicatedClass2 getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(ComplicatedClass2 innerClass) {
        this.innerClass = innerClass;
    }

    public ComplicatedClass2 getIgnoredClass() {
        return ignoredClass;
    }

    public void setIgnoredClass(ComplicatedClass2 ignoredClass) {
        this.ignoredClass = ignoredClass;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ComplicatedClass1 getNullClass() {
        return nullClass;
    }

    public void setNullClass(ComplicatedClass1 nullClass) {
        this.nullClass = nullClass;
    }
}
