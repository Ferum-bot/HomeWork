package com.ferumbot.mapper.impl.service.impl;

import com.ferumbot.mapper.impl.service.DateTimeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DefaultDateTimeService implements DateTimeService {

    @Override
    public String convertToString(LocalDate date, String pattern) {
        return null;
    }

    @Override
    public String convertToString(LocalTime time, String pattern) {
        return null;
    }

    @Override
    public String convertToString(LocalDateTime time, String pattern) {
        return null;
    }
}
