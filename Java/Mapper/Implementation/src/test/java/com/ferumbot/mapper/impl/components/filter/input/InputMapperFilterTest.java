package com.ferumbot.mapper.impl.components.filter.input;

import com.ferumbot.mapper.impl.components.filter.input.impl.InputStructureFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class InputMapperFilterTest {

    private InputMapperFilter inputStructureFilter;

    @BeforeEach
    void setUp() {
        inputStructureFilter = new InputStructureFilter();
    }

    @AfterEach
    void tearDown() {
        inputStructureFilter = null;
    }

}