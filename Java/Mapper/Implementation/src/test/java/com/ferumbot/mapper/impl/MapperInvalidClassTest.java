package com.ferumbot.mapper.impl;

import com.ferumbot.mapper.impl.classes.InvalidClass1;
import com.ferumbot.mapper.impl.classes.InvalidClass2;
import com.ferumbot.mapper.impl.classes.InvalidClass3;
import com.ferumbot.mapper.impl.service.impl.DefaultDateTimeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.homework4.Mapper;
import ru.hse.homework4.exceptions.IOException;
import ru.hse.homework4.exceptions.InvalidDateFormatException;
import ru.hse.homework4.exceptions.RetainCycleException;
import ru.hse.homework4.exceptions.UnSupportedClassException;

import static org.junit.jupiter.api.Assertions.*;

public class MapperInvalidClassTest {

    private Mapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DefaultMapper();
    }

    @AfterEach
    void tearDown() {
        mapper = null;
    }

    @Test
    void WriteToString_InvalidClass1_ThrowExceptionUnSupportedClass() throws IOException {
        var invalidClass = new InvalidClass1();

        assertThrows(UnSupportedClassException.class, (() -> {
            mapper.writeToString(invalidClass);
        }));
    }

    @Test
    void WriteToString_InvalidClass2_ThrowExceptionInvalidDateFormat() throws IOException {
        var invalidClass = new InvalidClass2();

        assertThrows(InvalidDateFormatException.class, (() -> {
            mapper.writeToString(invalidClass);
        }));
    }

    @Test
    void WriteToString_InvalidClass3_ThrowExceptionRetainCycle() throws IOException {
        var invalidClass = new InvalidClass3();

        assertThrows(RetainCycleException.class, (() -> {
            mapper.writeToString(invalidClass);
        }));
    }

}
