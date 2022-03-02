package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.service.impl.CommonSerializationTemplateService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class SerializationTemplatesServiceTest {

    private SerializationTemplatesService serializationTemplatesService;

    @BeforeEach
    void setUp() {
        serializationTemplatesService = new CommonSerializationTemplateService();
    }

    @AfterEach
    void tearDown() {
        serializationTemplatesService = null;
    }

}