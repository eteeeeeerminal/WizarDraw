package model;

import java.awt.*;
import java.util.ArrayList;

import event.DrawModelEvent;
import event.DrawModelListener;
import event.ModelEventMulticaster;
import fig.Figure;

public class DrawModel {
    protected ArrayList<Figure> fig;
    protected Figure drawingFigure;
    protected DrawModelListener listener;
    protected final PaletteAndBrush paletteBrush;

    public DrawModel(PaletteAndBrush p) {
        fig = new ArrayList<>();
        drawingFigure = null;
        paletteBrush = p;
    }
    public ArrayList<Figure> getFigures() {
        return fig;
    }
    public Figure getFigure(int idx) {
        return fig.get(idx);
    }
    public void setRectangle(boolean isFilled) {
        paletteBrush.setRectangle(isFilled);
    }
    public void createFigure(int x, int y) {
        Figure f = paletteBrush.createFigure(x, y);
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

    public Color getCurrentColor() {
        return paletteBrush.getBrushColor();
    }
    public void changeCurrentColor(int paletteNum) {
        paletteBrush.changeBrushColor(paletteNum);
    }
    public void changeCurrentColor(Color c) {
        paletteBrush.changeBrushColor(c);
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
}