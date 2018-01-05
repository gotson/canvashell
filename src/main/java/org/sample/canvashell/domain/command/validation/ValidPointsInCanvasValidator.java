package org.sample.canvashell.domain.command.validation;

import org.sample.canvashell.domain.command.CanvasAndPoints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPointsInCanvasValidator
        implements ConstraintValidator<ValidPointsInCanvas, CanvasAndPoints> {

    @Override
    public void initialize(ValidPointsInCanvas constraintAnnotation) {
    }

    @Override
    public boolean isValid(CanvasAndPoints command, ConstraintValidatorContext context) {
        if (command == null || command.getCanvas() == null) {
            return true;
        }

        return command.getPoints().stream().allMatch(point ->
                point.getX() > 0
                && point.getX() <= command.getCanvas().getWidth()
                && point.getY() > 0
                && point.getY() <= command.getCanvas().getHeight()
        );
    }
}
