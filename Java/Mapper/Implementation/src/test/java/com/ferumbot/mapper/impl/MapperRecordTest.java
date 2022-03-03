package com.ferumbot.mapper.impl;

import com.ferumbot.mapper.impl.classes.ComplicatedClass1;
import com.ferumbot.mapper.impl.classes.ComplicatedClass2;
import com.ferumbot.mapper.impl.enums.TestEnum;
import com.ferumbot.mapper.impl.records.InvalidRecord;
import com.ferumbot.mapper.impl.records.Record1;
import com.ferumbot.mapper.impl.records.Record2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.homework4.Mapper;
import ru.hse.homework4.exceptions.UnSupportedClassException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
                """;

        assertTrue((() -> actualResult.startsWith(expectedResult)));
    }

    @Test
    void WriteToString_Record2_SuccessSerializationToString() throws IOException {
        var record = new Record2(
            List.of(new ComplicatedClass2(), new ComplicatedClass2()), TestEnum.FIRST_VALUE
        );

        var actualResult = mapper.writeToString(record);

        var expectedPart1 = """
                "nullString" : null
                }
                {
                "$OBJECT_ID$" : 15
                "notes" : [
                "TestNote1"
                "TestNote2"
                "TestNote3"
                "TestNote4"
                ]
                "costs" : [
                23.0
                -123.1
                0.123
                123.3
                ]
                "testEnum" : SECOND_VALUE
                """;
        var expectedPart2 = """
                {
                "$OBJECT_ID$" : 0
                "first-class" : [
                {
                "$OBJECT_ID$" : 2
                "notes" : [
                "TestNote1"
                "TestNote2"
                "TestNote3"
                "TestNote4"
                ]
                "costs" : [
                23.0
                -123.1
                0.123
                123.3
                ]
                "testEnum" : SECOND_VALUE
                """;

        assertTrue((() -> actualResult.contains(expectedPart1) && actualResult.contains(expectedPart2)));
    }

    @Test
    void WriteToString_InvalidRecord1_ThrowException() throws IOException {
        var record = new InvalidRecord(
            1, "test value", LocalDateTime.now()
        );

        assertThrows(UnSupportedClassException.class, (() -> {
            mapper.writeToString(record);
        }));
    }
}
