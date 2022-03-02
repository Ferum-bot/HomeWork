package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.service.impl.DefaultDateTimeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    private DateTimeService dateTimeService;

    @BeforeEach
    void setUp() {
        dateTimeService = new DefaultDateTimeService();
    }

    @AfterEach
    void tearDown() {
        dateTimeService = null;
    }

}