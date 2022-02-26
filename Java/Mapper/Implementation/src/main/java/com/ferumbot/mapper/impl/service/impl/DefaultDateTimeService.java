package com.ferumbot.mapper.impl.service.impl;

import com.ferumbot.mapper.impl.service.DateTimeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DefaultDateTimeService implements DateTimeService {

    @Override
    public String convertToString(LocalDate date, String pattern) {
        var formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    @Override
    public String convertToString(LocalTime time, String pattern) {
        var formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    @Override
    public String convertToString(LocalDateTime time, String pattern) {
        var formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }
}
