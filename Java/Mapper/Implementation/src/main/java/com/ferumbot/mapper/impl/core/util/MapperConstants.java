package com.ferumbot.mapper.impl.core.util;

import java.time.format.DateTimeFormatter;

public class MapperConstants {

    public static final String LOCAL_TIME_PATTERN = "hh.mm.ss";
    public static final String LOCAL_DATE_PATTERN = "dd.MM.yyyy";
    public static final String LOCAL_DATE_TIME_PATTERN = "dd.MM.yyyy:hh.mm.ss";

    public static final DateTimeFormatter LOCAL_TIME_FORMAT = DateTimeFormatter.ofPattern(LOCAL_TIME_PATTERN);
    public static final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN);
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN);

    public static final String SPACE = " ";
    public static final String NEW_LINE = "\n";

    public static final String NAME_SEPARATOR = ":";
    public static final String NAME_BEGIN_SYMBOL = "\"";
    public static final String NAME_END_SYMBOL = "\"";

    public static final String COLLECTION_BEGIN_SYMBOL = "[";
    public static final String COLLECTION_END_SYMBOL = "]";
    public static final String OBJECT_BEGIN_SYMBOL = "{";
    public static final String OBJECT_END_SYMBOL = "}";

    public static final String OBJECT_ID_ALIAS = "$OBJECT_ID$";
}
