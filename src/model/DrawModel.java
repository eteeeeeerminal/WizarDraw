package model;

import event.CommandEvent;
import event.CommandListener;

import java.awt.*;
import java.util.function.Function;

public class DrawModel implements CommandListener {
    protected final PaletteAndBrush paletteBrush;
    protected final Canvas canvas;

    public DrawModel(PaletteAndBrush p, Canvas c) {
        paletteBrush = p;
        canvas = c;
    }
    public void createFigure(int x, int y) {
        canvas.createFigure(paletteBrush.createFigure(x, y));
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
        canvas.reshapeFigure(x1, y1, x2, y2);
    }

    public void commandPerformed(CommandEvent e) {
        switch (e.getCommand()) {
            case QUIT:
                System.exit(0);
                break;
            case CHANGE_COLOR:
                Function<Color, Color> f = e.getChangeColorFunction();
                paletteBrush.changeBrushColor(
                        f.apply(paletteBrush.getBrushColor())
                );
                break;
            case PALETTE1:
                paletteBrush.changeBrushColor(0);
                break;
            case PALETTE2:
                paletteBrush.changeBrushColor(1);
                break;
            case PALETTE3:
                paletteBrush.changeBrushColor(2);
                break;
            case RECT:
                paletteBrush.setRectangle(false);
                break;
            case FILLED_RECT:
                paletteBrush.setRectangle(true);
                break;
        }
    }
}