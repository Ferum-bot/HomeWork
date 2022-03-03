package com.ferumbot.mapper.impl.records;

import com.ferumbot.mapper.impl.classes.ComplicatedClass2;
import com.ferumbot.mapper.impl.enums.TestEnum;
import ru.hse.homework4.Ignored;
import ru.hse.homework4.PropertyName;

import java.util.List;

public record Record2(
    @PropertyName("first-class")
    List<ComplicatedClass2> secondClass,

    @Ignored
    TestEnum currentEnum
) {
}
