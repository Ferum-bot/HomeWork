package com.ferumbot.jigsaw.core.support;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomValueGeneratorTest {

    @Test
    void GenerateInt_CommonInput_IntGenerated() {
        assertDoesNotThrow((() -> {
            RandomValueGenerator.generateInt(10);
            RandomValueGenerator.generateInt(1234);
            RandomValueGenerator.generateInt(3424234);
        }));
    }

    @Test
    void GenerateInt_CommonInput_IntLessThanBound() {
        var bound1 = 10;
        var result1 = RandomValueGenerator.generateInt(bound1);

        var bound2 = 2;
        var result2 = RandomValueGenerator.generateInt(bound2);

        assertTrue(result2 < bound2);
        assertTrue(result1 <  bound1);
    }

}