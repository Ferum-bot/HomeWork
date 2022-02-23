package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.core.models.GraphNode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface DateTimeService {

    String convertToString(LocalDate date, String pattern);

    String convertToString(LocalTime time, String pattern);

    String convertToString(LocalDateTime time, String pattern);
}
