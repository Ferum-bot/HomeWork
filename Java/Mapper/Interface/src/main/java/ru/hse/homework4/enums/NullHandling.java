package ru.hse.homework4.enums;

import ru.hse.homework4.Mapper;
import ru.hse.homework4.Exported;

/**
 * Defines null handling policy.
 * @see Mapper
 * @see Exported
 * @author matvejpopov
 * @version 1.0.0
 */
public enum NullHandling {
    EXCLUDE,
    INCLUDE;
}
