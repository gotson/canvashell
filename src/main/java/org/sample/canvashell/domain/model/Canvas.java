package org.sample.canvashell.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Data
public class Canvas {
    @Getter(AccessLevel.PRIVATE)
    private final Pixel[][] grid;
    @Positive
    private final int width;
    @Positive
    private final int height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Pixel[height][width];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Pixel(' ');
            }
        }
    }

    public Pixel getPixel(Point point) {
        return getPixel(point.getX(), point.getY());
    }

    public Pixel getPixel(int x, int y) {
        return grid[y - 1][x - 1];
    }

    public void setPixel(Point point, Pixel pixel) {
        setPixel(point.getX(), point.getY(), pixel);
    }

    public void setPixel(int x, int y, Pixel pixel) {
        grid[y - 1][x - 1] = pixel;
    }
}
