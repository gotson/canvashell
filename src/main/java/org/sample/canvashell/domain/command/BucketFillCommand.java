package org.sample.canvashell.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sample.canvashell.domain.command.validation.ValidPointsInCanvas;
import org.sample.canvashell.domain.model.Canvas;
import org.sample.canvashell.domain.model.Pixel;
import org.sample.canvashell.domain.model.Point;

import javax.validation.constraints.NotNull;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ValidPointsInCanvas(message = "The point is outside the canvas")
public class BucketFillCommand extends Command implements CanvasAndPoints {
    @NotNull(message = "Canvas has to be initialized first")
    private final Canvas canvas;
    private final Point from;
    private final char replacementColor;

    @Override
    public void execute() {
        char targetColor = canvas.getPixel(from).getColor();
        Queue<Point> queue = new LinkedList<>();
        queue.add(from);

        while (!queue.isEmpty()) {
            Point current = queue.remove();
            canvas.setPixel(current, new Pixel(replacementColor));

            List<Point> neighbors = new ArrayList<>();
            neighbors.add(new Point(current.getX() - 1, current.getY()));
            neighbors.add(new Point(current.getX() + 1, current.getY()));
            neighbors.add(new Point(current.getX(), current.getY() - 1));
            neighbors.add(new Point(current.getX(), current.getY() + 1));

            neighbors.forEach(point -> {
                if (point.getX() > 0 && point.getX() <= canvas.getWidth()
                        && point.getY() > 0 && point.getY() <= canvas.getHeight()
                        && canvas.getPixel(point).getColor() == targetColor){
                    queue.add(point);
                }
            });

        }
    }

    @Override
    public Collection<Point> getPoints() {
        return Collections.singletonList(from);
    }
}
