package com.ferumbot.mapper.impl.classes;

import ru.hse.homework4.DateFormat;
import ru.hse.homework4.Exported;
import ru.hse.homework4.Ignored;
import ru.hse.homework4.PropertyName;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Exported
public class ComplicatedClass1 {

    private final String name = "name";

    private final String surname = "surname";

    @Ignored
    private final String lastName = "lastName";

    private final long count = 124L;

    @PropertyName("last_price")
    private final Double price = 123.3;

    private final LocalDateTime currentDate = LocalDateTime.now();

    @DateFormat("dd-MM-yyyy")
    private final LocalDate lastDate = LocalDate.now();
}
