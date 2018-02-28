package org.sample.canvashell.domain.command;

import lombok.Getter;
import org.sample.canvashell.domain.command.validation.CommandValidationException;
import org.sample.canvashell.domain.model.Canvas;
import org.sample.canvashell.domain.model.CanvasRedrawException;
import org.sample.canvashell.domain.model.Point;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

public abstract class DrawingCommand {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @NotNull(message = "Canvas has to be initialized first")
    @Getter
    protected final Canvas canvas;

    private Canvas history;

    public abstract Collection<Point> getPoints();

    public DrawingCommand(Canvas canvas){
        this.canvas = canvas;
    }

    public final void validateAndExecute() throws CommandValidationException {
        validate();
        execute();
    }

    private void validate() throws CommandValidationException {
        Set<ConstraintViolation<DrawingCommand>> violations = validator.validate(this);
        if(!violations.isEmpty())
            throw new CommandValidationException(violations, "DrawingCommand is invalid");
    }

    public final void execute(){
        history = canvas.duplicate();
        executeSpecific();
    }

    abstract void executeSpecific();

    public void undo() throws CanvasRedrawException {
        if(history != null)
            canvas.redraw(history);
    }

}
