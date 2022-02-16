package ru.hse.homework4;

import ru.hse.homework4.enums.NullHandling;
import ru.hse.homework4.enums.UnknownPropertiesPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.hse.homework4.enums.NullHandling.EXCLUDE;
import static ru.hse.homework4.enums.UnknownPropertiesPolicy.FAIL;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Exported {

    NullHandling nullHandling() default EXCLUDE;

    UnknownPropertiesPolicy unknownPropertiesPolicy() default FAIL;
}
