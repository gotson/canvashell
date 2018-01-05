package org.sample.canvashell.domain.command.validation;

import lombok.Getter;
import org.sample.canvashell.domain.command.Command;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CommandValidationException extends Exception {
    @Getter
    private final Set<ConstraintViolation<Command>> violations;

    public CommandValidationException(Set<ConstraintViolation<Command>> violations, String message){
        super(message);
        this.violations = violations;
    }
}
