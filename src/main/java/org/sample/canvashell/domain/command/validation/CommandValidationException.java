package org.sample.canvashell.domain.command.validation;

import lombok.Getter;
import org.sample.canvashell.domain.command.DrawingCommand;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CommandValidationException extends Exception {
    @Getter
    private final Set<ConstraintViolation<DrawingCommand>> violations;

    public CommandValidationException(Set<ConstraintViolation<DrawingCommand>> violations, String message){
        super(message);
        this.violations = violations;
    }
}
