package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.processor.DeserializationProcessor;
import com.ferumbot.mapper.impl.processor.SerializationProcessor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Used to formatting date/time.
 * @see LocalDate
 * @see LocalTime
 * @see LocalDateTime
 * @see SerializationProcessor
 * @see DeserializationProcessor
 * @author matvejpopov
 * @version 1.0.0
 */
public interface DateTimeService {

    /**
     * Converts {@link LocalDate} to string.
     * @see LocalDate
     * @param date date value to convert from.
     * @param pattern the pattern to convert how.
     * @return converted date.
     */
    String convertToString(LocalDate date, String pattern);

    /**
     * Converts {@link LocalTime} to string.
     * @see LocalTime
     * @param time time value to convert from.
     * @param pattern the pattern to convert how.
     * @return converted time.
     */
    String convertToString(LocalTime time, String pattern);

    /**
     * Converts {@link LocalDateTime} to string.
     * @see LocalDateTime
     * @param time date/time value to convert from.
     * @param pattern the pattern to convert how.
     * @return converted date/time.
     */
    String convertToString(LocalDateTime time, String pattern);
}
