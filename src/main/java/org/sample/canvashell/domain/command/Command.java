package org.sample.canvashell.domain.command;

import org.sample.canvashell.domain.command.validation.CommandValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public abstract class Command {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public final void validateAndExecute() throws CommandValidationException {
        validate();
        execute();
    }

    private void validate() throws CommandValidationException {
        Set<ConstraintViolation<Command>> violations = validator.validate(this);
        if(!violations.isEmpty())
            throw new CommandValidationException(violations, "Command is invalid");
    }

    abstract void execute();
}
