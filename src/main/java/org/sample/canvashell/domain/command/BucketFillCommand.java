package org.sample.canvashell.domain.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sample.canvashell.domain.command.validation.ValidPointsInCanvas;
import org.sample.canvashell.domain.model.Canvas;
import org.sample.canvashell.domain.model.Pixel;
import org.sample.canvashell.domain.model.Point;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ValidPointsInCanvas(message = "The point is outside the canvas")
public class BucketFillCommand extends DrawingCommand {
    private final Point from;
    private final char replacementColor;

    public BucketFillCommand(Canvas canvas, Point from, char replacementColor){
        super(canvas);
        this.from = from;
        this.replacementColor = replacementColor;
    }

    @Override
    public void executeSpecific() {
        char originalColor = canvas.getPixel(from).getColor();

        if(originalColor == replacementColor)
            return;

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
                        && canvas.getPixel(point).getColor() == originalColor){
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
