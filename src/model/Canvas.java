package model;

import event.CanvasEvent;
import event.CanvasListener;
import event.ModelEventMulticaster;
import fig.Figure;

import java.util.ArrayList;

public class Canvas {
    protected ArrayList<Figure> fig;
    protected Figure selectedFigure;
    protected CanvasListener listener;

    public Canvas() {
        fig = new ArrayList<>();
        selectedFigure = null;
        listener = null;
    }
    public ArrayList<Figure> getFigures() {
        return fig;
    }
    public void createFigure(Figure f) {
        fig.add(f);
        selectedFigure = f;
        update();
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
        if (selectedFigure != null) {
            selectedFigure.reshape(x1, y1, x2, y2);
        }
        update();
    }
    public void addListener(CanvasListener l) {
        if (l == null) {
            return;
        }
        listener = ModelEventMulticaster.add(listener, l);
    }
    protected void update() {
        if (listener != null) {
            listener.canvasUpdated(new CanvasEvent(this));
        }
    }
}