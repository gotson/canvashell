package org.sample.canvashell.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sample.canvashell.domain.command.validation.ValidPointsInCanvas;
import org.sample.canvashell.domain.model.Canvas;
import org.sample.canvashell.domain.model.Point;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ValidPointsInCanvas(message = "One or more points are outside the canvas")
public class DrawRectangleCommand extends Command implements CanvasAndPoints {
    @NotNull(message = "Canvas has to be initialized first")
    private final Canvas canvas;
    private final Point from;
    private final Point to;

    @Override
    public void execute() {
        List<Command> subCommands = new ArrayList<>();
        subCommands.add(new DrawLineCommand(canvas, from, new Point(from.getX(), to.getY())));
        subCommands.add(new DrawLineCommand(canvas, from, new Point(to.getX(), from.getY())));
        subCommands.add(new DrawLineCommand(canvas, to, new Point(from.getX(), to.getY())));
        subCommands.add(new DrawLineCommand(canvas, to, new Point(to.getX(), from.getY())));

        subCommands.forEach(Command::execute);
    }

    @Override
    public Collection<Point> getPoints() {
        return Arrays.asList(from, to);
    }
}
