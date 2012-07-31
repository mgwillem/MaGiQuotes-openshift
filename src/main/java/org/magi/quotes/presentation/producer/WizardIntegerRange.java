package org.magi.quotes.presentation.producer;

import javax.inject.Qualifier;
import javax.swing.text.StyleContext;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author <a href="mailto:mgw@mmx.lu">Marc Gabriel-Willem</a>
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface WizardIntegerRange {

    public enum Range {
        SMALL_NOT_ZERO,
        SMALL,
        LARGE_NOT_ZERO,
        LARGE;
    }

    public Range range() default Range.SMALL;

}
