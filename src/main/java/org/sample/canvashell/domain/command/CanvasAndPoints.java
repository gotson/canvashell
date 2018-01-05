package org.sample.canvashell.domain.command;

import org.sample.canvashell.domain.model.Canvas;
import org.sample.canvashell.domain.model.Point;

import java.util.Collection;

public interface CanvasAndPoints {
    Canvas getCanvas();
    Collection<Point> getPoints();
}
