package com.ferumbot.mapper.impl.core.util;

import java.time.format.DateTimeFormatter;

public class MapperConstants {

    public static final String localTimePattern = "hh.mm.ss";
    public static final String localDatePattern = "dd.MM.yyyy";
    public static final String localDateTimePattern = "dd.MM.yyyy:hh.mm.ss";

    public static final DateTimeFormatter localTimeFormat = DateTimeFormatter.ofPattern(localTimePattern);
    public static final DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern(localDatePattern);
    public static final DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ofPattern(localDateTimePattern);
}
