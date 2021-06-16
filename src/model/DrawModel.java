package model;

import java.awt.*;
import java.util.ArrayList;

import event.DrawModelEvent;
import event.DrawModelListener;
import event.ModelEventMulticaster;
import fig.Figure;
import fig.RectangleFigure;

public class DrawModel {
    public static final int COLOR_PALETTE_SIZE=3;
    protected ArrayList<Figure> fig;
    protected Figure drawTool;
    protected Figure drawingFigure;
    protected DrawModelListener listener;
    protected Color[] palette = new Color[COLOR_PALETTE_SIZE];
    protected int currentColor;

    public DrawModel() {
        fig = new ArrayList<Figure>();
        drawingFigure = null;
        palette[0] = Color.RED;
        palette[1] = Color.GREEN;
        palette[2] = Color.BLUE;
        currentColor = 0;
        drawTool = new RectangleFigure(0,0,0,0, getCurrentColor());
    }
    public ArrayList<Figure> getFigures() {
        return fig;
    }
    public Figure getFigure(int idx) {
        return fig.get(idx);
    }
    public void setRectangle(boolean isFilled) {
        drawTool = new RectangleFigure(0,0,0,0, getCurrentColor(), isFilled);
        updatePalette();
    }
    public void createFigure(int x, int y) {
        Figure f = drawTool.clone();
        f.setLocation(x, y);
        f.setColor(getCurrentColor());
        fig.add(f);
        drawingFigure = f;
        updateCanvas();
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
        if (drawingFigure != null) {
            drawingFigure.reshape(x1, y1, x2, y2);
            updateCanvas();
        }
    }

    public Color[] getPalette() {
        return palette;
    }
    public Color getCurrentColor() {
        return palette[currentColor];
    }
    public void changeCurrentColor(int paletteNum) {
        if(paletteNum < 0 || paletteNum >= COLOR_PALETTE_SIZE) {
            throw new Error("Invalid paletteNum");
        }
        currentColor = paletteNum;
        updatePalette();
    }
    public void changeCurrentColor(Color c) {
        palette[currentColor] = c;
        updatePalette();
    }

    public void addListener(DrawModelListener listener) {
        if (listener == null) {
            return;
        }
        this.listener = ModelEventMulticaster.add(this.listener, listener);
    }
    public void updateCanvas() {
        listener.canvasUpdated(new DrawModelEvent(this));
    }
    public void updatePalette() {
        listener.paletteUpdated(new DrawModelEvent(this));
    }
}