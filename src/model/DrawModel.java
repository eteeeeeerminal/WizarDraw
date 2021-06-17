package model;

import java.awt.*;

public class DrawModel {
    protected final PaletteAndBrush paletteBrush;
    protected final Canvas canvas;

    public DrawModel(PaletteAndBrush p, Canvas c) {
        paletteBrush = p;
        canvas = c;
    }
    public void setRectangle(boolean isFilled) {
        paletteBrush.setRectangle(isFilled);
    }
    public void createFigure(int x, int y) {
        canvas.createFigure(paletteBrush.createFigure(x, y));
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
        canvas.reshapeFigure(x1, y1, x2, y2);
    }

    public Color getCurrentColor() {
        return paletteBrush.getBrushColor();
    }
    public void changeCurrentColor(int paletteNum) {
        paletteBrush.changeBrushColor(paletteNum);
    }
    public void changeCurrentColor(Color c) {
        paletteBrush.changeBrushColor(c);
    }
}