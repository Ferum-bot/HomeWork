package com.ferumbot.mapper.impl.records;

import com.ferumbot.mapper.impl.classes.ComplicatedClass1;
import ru.hse.homework4.Exported;
import ru.hse.homework4.PropertyName;

import java.util.Set;

@Exported
public record Record1(
    String firstField,

    Short secondField,

    @PropertyName("OUTER_CLASS")
    ComplicatedClass1 innerClass,

    Set<Float> collection
) {
}
