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
@Constraint(validatedBy = {ValidPointsInCanvasValidator.class})
@Documented
public @interface ValidPointsInCanvas {

    String message() default "{com.example.shell.domain.service.command." + "ValidPointsInCanvas.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

