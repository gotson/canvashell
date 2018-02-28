package org.sample.canvashell.domain.command.validation;

import org.sample.canvashell.domain.command.DrawingCommand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPointsInCanvasValidator
        implements ConstraintValidator<ValidPointsInCanvas, DrawingCommand> {

    @Override
    public void initialize(ValidPointsInCanvas constraintAnnotation) {
    }

    @Override
    public boolean isValid(DrawingCommand command, ConstraintValidatorContext context) {
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
