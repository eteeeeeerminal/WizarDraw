package model;

import java.awt.*;
import java.util.ArrayList;

import fig.Figure;
import fig.RectangleFigure;

////////////////////////////////////////////////
// Model (M)
public class DrawModel {
    protected ArrayList<Figure> fig;
    protected Figure drawingFigure;
    protected Color currentColor;
    protected DrawEventListener listener;

    public DrawModel() {
        fig = new ArrayList<Figure>();
        drawingFigure = null;
        currentColor = Color.red;
    }
    public ArrayList<Figure> getFigures() {
        return fig;
    }
    public Figure getFigure(int idx) {
        return fig.get(idx);
    }
    public void createFigure(int x, int y) {
        Figure f = new RectangleFigure(x, y, 0, 0, currentColor);
        fig.add(f);
        drawingFigure = f;
        update();
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
        if (drawingFigure != null) {
            drawingFigure.reshape(x1, y1, x2, y2);
            update();
        }
    }
    public void changeColor(Color c) {
        currentColor = c;
    }

    public void addListener(DrawEventListener listener) {
        this.listener = listener;
    }
    public void update() {
        listener.modelUpdated(new DrawEvent(this));
    }
}