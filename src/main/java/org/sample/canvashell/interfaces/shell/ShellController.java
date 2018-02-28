package org.sample.canvashell.interfaces.shell;

import org.sample.canvashell.domain.command.*;
import org.sample.canvashell.domain.command.validation.CommandValidationException;
import org.sample.canvashell.domain.model.Canvas;
import org.sample.canvashell.domain.model.CanvasRedrawException;
import org.sample.canvashell.domain.model.Point;
import org.sample.canvashell.infrastructure.persistence.CanvasInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.stream.Collectors;

@ShellComponent
public class ShellController {

    private final CanvasInMemoryRepository canvasRepository;
    private final CommandManager commandManager = new CommandManager();

    @Autowired
    public ShellController(CanvasInMemoryRepository canvasRepository) {
        this.canvasRepository = canvasRepository;
    }


    @ShellMethod(value = "Exit the shell.", key = {"Q"})
    public void quit() {
        throw new ExitRequest();
    }


    @ShellMethod(value = "Create a new canvas of width w and height h", key = {"C"})
    public String createCanvas(int w, int h) {
        canvasRepository.setCanvas(new Canvas(w, h));
        return printCanvas();
    }


    @ShellMethod(value = "Draw a line from (x1,y1) to (x2,y2).", key = {"L"})
    public String drawLine(int x1, int y1, int x2, int y2) {
        DrawingCommand command = new DrawLineCommand(canvasRepository.getCanvas(), new Point(x1, y1), new Point(x2, y2));

        return executeCommand(command);
    }


    @ShellMethod(value = "Draw a rectangle whose opposite corners are (x1,y1) and (x2,y2).", key = {"R"})
    public String drawRectangle(int x1, int y1, int x2, int y2) {
        DrawingCommand command = new DrawRectangleCommand(canvasRepository.getCanvas(), new Point(x1, y1), new Point(x2, y2));

        return executeCommand(command);
    }


    @ShellMethod(value = "Fill the entire area connected to (x,y) with color 'c'", key = {"B"})
    public String bucketFill(int x, int y, char c) {
        DrawingCommand command = new BucketFillCommand(canvasRepository.getCanvas(), new Point(x, y), c);

        return executeCommand(command);
    }

    @ShellMethod(value = "Undo last command", key = {"U"})
    public String undo(){
        try {
            commandManager.undo();
        } catch (CanvasRedrawException e) {
            return e.getMessage();
        }

        return printCanvas();
    }

    @ShellMethod(value = "Redo last undoed command", key = {"D"})
    public String redo(){
        try {
            commandManager.redo();
        } catch (CommandValidationException e) {
            return e.getMessage();
        }

        return printCanvas();
    }



    private String printCanvas() {
        if (canvasRepository.getCanvas() == null)
            return "Canvas has to be initialized first";

        StringBuilder sb = new StringBuilder();
        String topBottom = String.join("", Collections.nCopies(canvasRepository.getCanvas().getWidth() + 2, "-"));
        sb.append(topBottom).append("\n");

        printInnerCanvas(canvasRepository.getCanvas(), sb, "|");

        sb.append(topBottom).append("\n");
        return sb.toString();
    }

    private void printInnerCanvas(Canvas canvas, StringBuilder sb, String verticalDelimiter) {
        for (int i = 1; i <= canvas.getHeight(); i++) {
            sb.append(verticalDelimiter);
            for (int j = 1; j <= canvas.getWidth(); j++) {
                sb.append(canvas.getPixel(j, i).getColor());
            }
            sb.append(verticalDelimiter).append("\n");
        }
    }

    private String executeCommand(DrawingCommand command) {
        try {
            commandManager.executeCommand(command);
        } catch (CommandValidationException e) {
            return e.getViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }
        return printCanvas();
    }
}