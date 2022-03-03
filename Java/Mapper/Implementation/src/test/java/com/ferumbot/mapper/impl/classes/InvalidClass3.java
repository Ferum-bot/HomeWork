package com.ferumbot.mapper.impl.classes;

import ru.hse.homework4.Exported;
import ru.hse.homework4.PropertyName;
import ru.hse.homework4.enums.NullHandling;

@Exported(
    nullHandling = NullHandling.INCLUDE
)
public class InvalidClass3 {

    @PropertyName(value = "test_field2")
    private String field1;

    @PropertyName(value = "test_field1")
    private String field2;

    private InvalidClass3 invalidClass;

    public InvalidClass3() {
        field1 = null;
        field2 = null;

        invalidClass = this;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public InvalidClass3 getInvalidClass() {
        return invalidClass;
    }

    public void setInvalidClass(InvalidClass3 invalidClass) {
        this.invalidClass = invalidClass;
    }
}
