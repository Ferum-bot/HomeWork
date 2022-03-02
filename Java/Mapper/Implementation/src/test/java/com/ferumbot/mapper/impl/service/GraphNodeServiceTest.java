package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.service.impl.CommonGraphNodeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class GraphNodeServiceTest {

    private GraphNodeService graphNodeService;

    @BeforeEach
    void setUp() {
        graphNodeService = new CommonGraphNodeService();
    }

    @AfterEach
    void tearDown() {
        graphNodeService = null;
    }

}