package ru.hse.homework4;

import ru.hse.homework4.enums.NullHandling;
import ru.hse.homework4.enums.UnknownPropertiesPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.hse.homework4.enums.NullHandling.EXCLUDE;
import static ru.hse.homework4.enums.UnknownPropertiesPolicy.FAIL;

/**
 * Used to define class to serialize/deserialize by {@link Mapper}.
 * @see Mapper
 * @see Ignored
 * @see Exported
 * @see PropertyName
 * @author matvejpopov
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Exported {

    /**
     * Defines the null handling policy.
     * @see NullHandling
     * @return null handling policy.
     */
    NullHandling nullHandling() default EXCLUDE;

    /**
     * Defines the unknown property policy.
     * @see UnknownPropertiesPolicy
     * @return unknown property policy.
     */
    UnknownPropertiesPolicy unknownPropertiesPolicy() default FAIL;
}
