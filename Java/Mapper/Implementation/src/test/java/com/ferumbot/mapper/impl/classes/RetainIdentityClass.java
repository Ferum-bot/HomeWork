package com.ferumbot.mapper.impl.classes;

import ru.hse.homework4.Exported;

@Exported
public class RetainIdentityClass {

    private ComplicatedClass1 firstClass;
    private ComplicatedClass1 secondClass;

    private ComplicatedClass2 test1;
    private ComplicatedClass2 test2;
    private ComplicatedClass2 test3;

    public RetainIdentityClass() {
        firstClass = new ComplicatedClass1();
        secondClass = new ComplicatedClass1();

        test1 = new ComplicatedClass2();
        test2 = new ComplicatedClass2();
        test3 = new ComplicatedClass2();
    }

    public ComplicatedClass1 getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(ComplicatedClass1 firstClass) {
        this.firstClass = firstClass;
    }

    public ComplicatedClass1 getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(ComplicatedClass1 secondClass) {
        this.secondClass = secondClass;
    }

    public ComplicatedClass2 getTest1() {
        return test1;
    }

    public void setTest1(ComplicatedClass2 test1) {
        this.test1 = test1;
    }

    public ComplicatedClass2 getTest2() {
        return test2;
    }

    public void setTest2(ComplicatedClass2 test2) {
        this.test2 = test2;
    }

    public ComplicatedClass2 getTest3() {
        return test3;
    }

    public void setTest3(ComplicatedClass2 test3) {
        this.test3 = test3;
    }
}
