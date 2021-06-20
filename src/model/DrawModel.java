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

    public void commandPerformed(CommandEvent e) {
        switch (e.getCommand()) {
            case CREATE_FIGURE:
                canvas.createFigure(paletteBrush.createFigure(e.getPoint1().x, e.getPoint1().y));
                break;
            case RESHAPE_FIGURE:
                canvas.reshapeFigure(e.getPoint1().x, e.getPoint1().y, e.getPoint2().x, e.getPoint2().y);
                break;
            case UNDO:
                canvas.undo();
                break;
            case REDO:
                canvas.redo();
                break;
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