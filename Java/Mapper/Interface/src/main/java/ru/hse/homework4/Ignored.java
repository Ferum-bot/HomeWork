package ru.hse.homework4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to define ignoring property.
 * @see Mapper
 * @see Ignored
 * @see Exported
 * @see PropertyName
 * @author matvejpopov
 * @version 1.0.0
 */
@Target({
        ElementType.RECORD_COMPONENT,
        ElementType.FIELD,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignored {
}
