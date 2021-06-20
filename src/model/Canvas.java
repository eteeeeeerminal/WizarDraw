package model;

import event.CanvasEvent;
import event.CanvasListener;
import event.WizarDrawEventMulticaster;
import fig.Figure;

import java.util.ArrayDeque;

public class Canvas {
    protected ArrayDeque<Figure> canvasFigs = new ArrayDeque<>();
    protected ArrayDeque<Figure> deletedFigs = new ArrayDeque<>();
    protected Figure selectedFigure = null;
    protected CanvasListener listener = null;

    public ArrayDeque<Figure> getFigures() {
        return canvasFigs;
    }
    public void createFigure(Figure f) {
        canvasFigs.addFirst(f);
        selectedFigure = f;
        update();
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
        if (selectedFigure != null) {
            selectedFigure.reshape(x1, y1, x2, y2);
        }
        update();
    }
    public void undo() {
        if (canvasFigs.size() < 1) {
            return;
        }
        deletedFigs.addFirst(canvasFigs.removeFirst());
        update();
    }
    public void redo() {
        if (deletedFigs.size() < 1) {
            return;
        }
        canvasFigs.addFirst(deletedFigs.removeFirst());
        update();
    }
    public void addListener(CanvasListener l) {
        if (l == null) {
            return;
        }
        listener = WizarDrawEventMulticaster.add(listener, l);
    }
    protected void update() {
        if (listener != null) {
            listener.canvasUpdated(new CanvasEvent(this));
        }
    }
}