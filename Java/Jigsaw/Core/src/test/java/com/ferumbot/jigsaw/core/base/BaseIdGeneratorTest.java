package com.ferumbot.jigsaw.core.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseIdGeneratorTest {

    @Test
    void GenerateId_CommonInput_IdGenerated() {
        var first = 1;
        var second = 2;

        var actualId = BaseIdGenerator.generateId(first, second);
        var expectedId = String.valueOf(1) + BaseIdGenerator.SEPARATOR + 2;

        assertEquals(expectedId, actualId);
    }

    @Test
    void GenerateId_NegativeInput_IdGenerated() {
        var first = -2;
        var second = 2;

        var actualId = BaseIdGenerator.generateId(first, second);
        var expectedId = String.valueOf(-2) + BaseIdGenerator.SEPARATOR  + 2;

        assertEquals(expectedId, actualId);
    }

    @Test
    void ParseId_CommonInput_IdParsed() {
        var idString = "1" + BaseIdGenerator.SEPARATOR + 2;

        var actualResult = BaseIdGenerator.parseId(idString);
        var expectedResult = new BaseIdGenerator.Pair(1, 2);

        assertEquals(expectedResult.first(), actualResult.first());
        assertEquals(expectedResult.first(), actualResult.first());
    }

}