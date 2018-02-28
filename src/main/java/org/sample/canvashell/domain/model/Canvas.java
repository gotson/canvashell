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

    public Canvas(Canvas copy){
        this.width = copy.width;
        this.height = copy.height;
        grid = new Pixel[height][width];
        copyGrid(copy);
    }

    public Canvas duplicate(){
        return new Canvas(this);
    }

    private void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Pixel(' ');
            }
        }
    }

    private void copyGrid(Canvas source) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = source.grid[i][j].duplicate();
            }
        }
    }

    public void redraw(Canvas source) throws CanvasRedrawException {
        if(width != source.width || height !=source.height)
            throw new CanvasRedrawException("Cannot redraw, source and destination are not the same size");

        copyGrid(source);
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
