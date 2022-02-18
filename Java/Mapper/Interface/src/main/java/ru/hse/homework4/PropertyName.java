package ru.hse.homework4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to define custom property name when {@link Mapper}
 * serialize or deserialize objects.
 * @see Mapper
 * @see Ignored
 * @see DateFormat
 * @see Exported
 * @author matvejpopov
 * @version 1.0.0
 */
@Target({
    ElementType.RECORD_COMPONENT,
    ElementType.FIELD,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyName {

    /**
     * Define new property value. If empty, used name from class.
     * @return defined property name.
     */
    String value() default "";
}
