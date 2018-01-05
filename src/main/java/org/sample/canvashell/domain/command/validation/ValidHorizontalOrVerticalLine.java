package org.sample.canvashell.domain.command.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidHorizontalOrVerticalLineValidator.class})
@Documented
public @interface ValidHorizontalOrVerticalLine {

    String message() default "{com.example.shell.domain.service.command." + "ValidHorizontalOrVerticalLine.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

