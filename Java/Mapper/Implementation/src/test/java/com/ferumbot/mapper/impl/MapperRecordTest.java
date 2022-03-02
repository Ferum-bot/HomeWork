package com.ferumbot.mapper.impl;

import com.ferumbot.mapper.impl.classes.ComplicatedClass1;
import com.ferumbot.mapper.impl.records.Record1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.homework4.Mapper;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
    void WriteToString_Record1_SuccessSerializationToString() throws IOException {
        var record = new Record1(
            "test1", (short) 2, new ComplicatedClass1(), Set.of(12.3f, -2f, 0.002f)
        );

        var actualResult = mapper.writeToString(record);

        var expectedResult = """
                {
                "$OBJECT_ID$" : 0
                "firstField" : "test1"
                "secondField" : 2
                "OUTER_CLASS" : {
                "$OBJECT_ID$" : 3
                "name" : "name"
                "surname" : "surname"
                "count" : 124
                "last_price" : 123.3
                "currentDate" : "02.03.2022-23:58:58"
                "lastDate" : "02-03-2022"
                }
                "collection" : [
                0.002
                12.3
                -2.0
                ]
                }
                """;


        assertEquals(expectedResult, actualResult);
    }

    @Test
    void WriteToString_Record2_SuccessSerializationToString() throws IOException {

    }

    @Test
    void WriteToString_InvalidRecord1_ThrowException() throws IOException {

    }
}
