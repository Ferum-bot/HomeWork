package com.ferumbot.mapper.impl.records;

import java.time.LocalDateTime;

public record InvalidRecord(
    Integer firstField,
    String newValue,
    LocalDateTime createdAt
) {
}
