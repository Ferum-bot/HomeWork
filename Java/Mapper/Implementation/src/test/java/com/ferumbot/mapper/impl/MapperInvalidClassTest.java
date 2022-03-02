package com.ferumbot.mapper.impl;

import com.ferumbot.mapper.impl.service.impl.DefaultDateTimeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.homework4.Mapper;
import ru.hse.homework4.exceptions.IOException;

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
    void WriteToString_InvalidClass1_ThrowException() throws IOException {

    }

    @Test
    void WriteToString_InvalidClass2_ThrowException() throws IOException {

    }
}
