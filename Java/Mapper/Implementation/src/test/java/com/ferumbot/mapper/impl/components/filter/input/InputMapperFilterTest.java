package com.ferumbot.mapper.impl.components.filter.input;

import com.ferumbot.mapper.impl.components.filter.input.impl.InputGrammarFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class InputMapperFilterTest {

    private InputMapperFilter inputStructureFilter;

    @BeforeEach
    void setUp() {
        inputStructureFilter = new InputGrammarFilter();
    }

    @AfterEach
    void tearDown() {
        inputStructureFilter = null;
    }

}