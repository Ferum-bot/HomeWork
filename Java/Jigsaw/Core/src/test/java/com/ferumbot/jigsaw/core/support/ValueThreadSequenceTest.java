package com.ferumbot.jigsaw.core.support;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueThreadSequenceTest {

    @Test
    void GenerateInt_CommonInput_IntGenerated() {
        assertDoesNotThrow(() -> {
            ValueThreadSequence.generateInt();
            ValueThreadSequence.generateInt();
            ValueThreadSequence.generateInt();
        });
    }

    @Test
    void FlushToInitial_CommonInput_IntFlushed() {
        assertDoesNotThrow(() -> {
            ValueThreadSequence.generateInt();
            ValueThreadSequence.generateInt();
        });

        ValueThreadSequence.flushToInitial();
        var actualResult = ValueThreadSequence.generateInt();
        var expectedResult = 0;

        assertEquals(expectedResult, actualResult);
    }
}