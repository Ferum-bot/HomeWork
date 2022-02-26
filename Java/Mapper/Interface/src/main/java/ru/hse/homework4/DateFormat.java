package ru.hse.homework4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to define pattern of date/time format.
 * @apiNote Supports only {@link java.time.LocalDate}, {@link java.time.LocalTime}
 * and {@link java.time.LocalDateTime} classes.
 * @see Mapper
 * @see Exported
 * @see Ignored
 * @see PropertyName
 * @author matvejpopov
 * @version 1.0.0
 */
@Target({
    ElementType.RECORD_COMPONENT,
    ElementType.FIELD,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {

    /**
     * Defines custom date/time pattern format.
     * @apiNote if empty, used dd.MM.yyyy format for {@link java.time.LocalDate},
     * hh.mm.ss for {@link java.time.LocalTime} and dd.MM.yyyy:hh.mm.ss for {@link java.time.LocalDateTime} class.
     * @return the date/time format.
     */
    String value() default "";
}
