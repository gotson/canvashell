package org.sample.canvashell.domain.command.validation;

import org.sample.canvashell.domain.command.DrawLineCommand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidHorizontalOrVerticalLineValidator
        implements ConstraintValidator<ValidHorizontalOrVerticalLine, DrawLineCommand> {

    @Override
    public void initialize(ValidHorizontalOrVerticalLine constraintAnnotation) {
    }

    @Override
    public boolean isValid(DrawLineCommand command, ConstraintValidatorContext context) {
        if (command == null) {
            return true;
        }

        return command.getFrom().getX() == command.getTo().getX()
                || command.getFrom().getY() == command.getTo().getY();
    }
}
