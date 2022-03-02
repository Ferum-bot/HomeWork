package com.ferumbot.mapper.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.homework4.Mapper;

public class MapperRecordTest {

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
    void WriteToString_Record1_SuccessSerializationToString() {

    }

    @Test
    void WriteToString_Record2_SuccessSerializationToString() {

    }

    @Test
    void WriteToString_InvalidRecord1_ThrowException() {

    }
}
