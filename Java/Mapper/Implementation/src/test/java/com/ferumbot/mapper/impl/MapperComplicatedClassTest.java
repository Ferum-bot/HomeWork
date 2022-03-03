package com.ferumbot.mapper.impl;

import com.ferumbot.mapper.impl.classes.ComplicatedClass1;
import com.ferumbot.mapper.impl.classes.ComplicatedClass2;
import com.ferumbot.mapper.impl.classes.ComplicatedClass3;
import com.ferumbot.mapper.impl.classes.RetainIdentityClass;
import com.ferumbot.mapper.impl.core.enums.ObjectType;
import com.ferumbot.mapper.impl.core.util.MapperConstants;
import com.ferumbot.mapper.impl.service.DateTimeService;
import com.ferumbot.mapper.impl.service.impl.DefaultDateTimeService;
import com.ferumbot.mapper.impl.util.StringUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hse.homework4.Mapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class MapperComplicatedClassTest {

    private Mapper mapper;

    private DateTimeService dateTimeService;

    @BeforeEach
    void setUp() {
        mapper = new DefaultMapper();
        dateTimeService = new DefaultDateTimeService();
    }

    @AfterEach
    void tearDown() {
        mapper = null;
        dateTimeService = null;
    }

    @Test
    void WriteToString_SimpleClass1_SuccessSerializationToString() throws IOException {
        var simpleObject1 = "Test String";
        var simpleObject2 = 123;
        var simpleObject3 = 13.4;
        var simpleObject4 = false;

        var actualResult1 = mapper.writeToString(simpleObject1);
        var actualResult2 = mapper.writeToString(simpleObject2);
        var actualResult3 = mapper.writeToString(simpleObject3);
        var actualResult4 = mapper.writeToString(simpleObject4);

        var expectedResult1 = "\"Test String\"\n";
        var expectedResult2 = "123\n";
        var expectedResult3 = "13.4\n";
        var expectedResult4 = "false\n";

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
        assertEquals(expectedResult4, actualResult4);
    }

    @Test
    void WriteToString_SimpleClass2_SuccessSerializationToString() throws IOException {
        var simpleObject1 = LocalDateTime.now();
        var simpleObject2 = LocalDate.now();
        var simpleObject3 = LocalTime.now();

        var actualResult1 = mapper.writeToString(simpleObject1);
        var actualResult2 = mapper.writeToString(simpleObject2);
        var actualResult3 = mapper.writeToString(simpleObject3);

        var rawExpectedResult1 = dateTimeService.convertToString(simpleObject1, MapperConstants.LOCAL_DATE_TIME_PATTERN);
        var rawExpectedResult2 = dateTimeService.convertToString(simpleObject2, MapperConstants.LOCAL_DATE_PATTERN);
        var rawExpectedResult3 = dateTimeService.convertToString(simpleObject3, MapperConstants.LOCAL_TIME_PATTERN);

        var expectedResult1 = "\"" + rawExpectedResult1 + "\"\n";
        var expectedResult2 = "\"" + rawExpectedResult2 + "\"\n";
        var expectedResult3 = "\"" + rawExpectedResult3 + "\"\n";

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    void WriteToString_SimpleClass3_SuccessSerializationToString() throws IOException {
        var simpleObject1 = ObjectType.EXPORTED_CLASS;
        var simpleObject2 = List.of("test1", "test2", "test3");
        var simpleObject3 = new TreeSet<>(List.of("test4", "test5", "test6"));

        var actualResult1 = mapper.writeToString(simpleObject1);
        var actualResult2 = mapper.writeToString(simpleObject2);
        var actualResult3 = mapper.writeToString(simpleObject3);

        var expectedResult1 = ObjectType.EXPORTED_CLASS
                + MapperConstants.NEW_LINE;
        var expectedResult2 = MapperConstants.COLLECTION_BEGIN_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.NAME_BEGIN_SYMBOL + "test1" + MapperConstants.NAME_END_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.NAME_BEGIN_SYMBOL + "test2" + MapperConstants.NAME_END_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.NAME_BEGIN_SYMBOL + "test3" + MapperConstants.NAME_END_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.COLLECTION_END_SYMBOL +
                MapperConstants.NEW_LINE;
        var expectedResult3 = MapperConstants.COLLECTION_BEGIN_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.NAME_BEGIN_SYMBOL + "test4" + MapperConstants.NAME_END_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.NAME_BEGIN_SYMBOL + "test5" + MapperConstants.NAME_END_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.NAME_BEGIN_SYMBOL + "test6" + MapperConstants.NAME_END_SYMBOL +
                MapperConstants.NEW_LINE +
                MapperConstants.COLLECTION_END_SYMBOL +
                MapperConstants.NEW_LINE;


        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    void WriteToString_ComplicatedClass1_SuccessSerializationToString() throws IOException {
        var localDateTime = LocalDateTime.now();
        var localDate = LocalDate.now();
        var complicatedClass = new ComplicatedClass1();
        var dateTimeFormatter = DateTimeFormatter.ofPattern(MapperConstants.LOCAL_DATE_TIME_PATTERN);
        var dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        complicatedClass.setCurrentDate(localDateTime);
        complicatedClass.setLastDate(localDate);

        var actualResult = mapper.writeToString(complicatedClass);

        var expectedDateTime = localDateTime.format(dateTimeFormatter);
        var expectedDate = localDateTime.format(dateFormatter);
        var expectedResult = new StringBuilder()
                .append("""
                        {
                        "$OBJECT_ID$" : 0
                        "name" : "name"
                        "surname" : "surname"
                        "count" : 124
                        "last_price" : 123.3
                        "currentDate" : \"""")
                .append(expectedDateTime)
                .append("\"\n")
                .append("""
                        "lastDate" : \"""")
                .append(expectedDate)
                .append("\"\n")
                .append("""
                        }
                        """)
                .toString();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void WriteToString_ComplicatedClass2_SuccessSerializationToString() throws IOException {
        var localTime = LocalTime.now();
        var complicatedClass = new ComplicatedClass2();
        var timeFormatter = DateTimeFormatter.ofPattern(MapperConstants.LOCAL_TIME_PATTERN);
        complicatedClass.setExportedTime(localTime);

        var actualResult = mapper.writeToString(complicatedClass);

        var expectedTime = localTime.format(timeFormatter);
        var expectedResult = new StringBuilder()
                .append("""
                        {
                        "$OBJECT_ID$" : 0
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
                        "exportedTime" : \"""")
                .append(expectedTime)
                .append("\"\n")
                .append("""
                        "nullString" : null
                        }
                        """)
                .toString();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void WriteToString_ComplicatedClass3_SuccessSerializationToString() {
        var complicatedClass = new ComplicatedClass3();

        assertDoesNotThrow(() -> mapper.writeToString(complicatedClass));
    }

    @Test
    void WriteToString_RetainIdentityClass_SuccessSerializationWithWriteIds() throws IOException {
        var retainClass = new RetainIdentityClass();
        var firstClass = new ComplicatedClass1();
        var testClass = new ComplicatedClass2();
        retainClass.setFirstClass(firstClass);
        retainClass.setSecondClass(firstClass);
        retainClass.setTest1(testClass);
        retainClass.setTest2(testClass);
        retainClass.setTest3(testClass);

        var actualResult = mapper.writeToString(retainClass);

        var expectedFirstClassId = """
                "$OBJECT_ID$" : 1
                """;
        var expectedTestClassId = """
                "$OBJECT_ID$" : 8
                """;
        var expectedFirstClassCount = 2;
        var expectedTestClassCount = 3;

        var actualFirstClassCount = StringUtil.countEntries(expectedFirstClassId, actualResult);
        var actualTestClassCount = StringUtil.countEntries(expectedTestClassId, actualResult);

        assertEquals(expectedFirstClassCount, actualFirstClassCount);
        assertEquals(expectedTestClassCount, actualTestClassCount);
    }

    @Test
    void WriteToInputStream_ComplicatedClass1_SuccessSerializationToStream() throws IOException {

    }

    @Test
    void WriteToFile_ComplicatedClass1_SuccessSerializationToFile() throws IOException {

    }
}