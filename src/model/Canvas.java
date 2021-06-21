package model;

import event.CanvasEvent;
import event.CanvasListener;
import event.WizarDrawEventMulticaster;
import fig.Figure;
import fig.SelectHighlight;

import java.util.ArrayDeque;

public class Canvas {
    protected ArrayDeque<Figure> canvasFigs = new ArrayDeque<>();
    protected ArrayDeque<Figure> deletedFigs = new ArrayDeque<>();
    protected Figure selectedFigure = null;
    protected Figure highlightFigure = new SelectHighlight(0,0,0,0);
    protected boolean visibleHighlight = false;
    protected CanvasListener listener = null;

    public ArrayDeque<Figure> getFigures() {
        ArrayDeque<Figure> figs = canvasFigs.clone();
        if (visibleHighlight && selectedFigure != null) {
            highlightFigure.setSize(selectedFigure.getWidth(), selectedFigure.getHeight());
            highlightFigure.setLocation(selectedFigure.getX(), selectedFigure.getY());
            figs.addLast(highlightFigure);
        }
        return figs;
    }
    public void createFigure(Figure f) {
        if (f == null) {
            return;
        }
        canvasFigs.addFirst(f);
        selectedFigure = f;
        update();
    }
    public void reshapeFigure(int x1, int y1, int x2, int y2) {
        if (selectedFigure == null) {
            return;
        }
        selectedFigure.reshape(x1, y1, x2, y2);
        update();
    }
    public void select(int x, int y) {
        boolean foundSelectedFig = selectedFigure == null;
        for (Figure fig: canvasFigs) {
            if (fig == selectedFigure) {
                foundSelectedFig = true;
                continue;
            }
            if (!fig.isInFigBox(x, y)) continue;
            selectedFigure = fig;
            if (foundSelectedFig) break;
        }
        update();
    }
    public void deselect() {
        selectedFigure = null;
        update();
    }
    public void delete() {
        deletedFigs.addFirst(selectedFigure);
        canvasFigs.remove(selectedFigure);
        selectedFigure = null;
        update();
    }
    public void setVisibleHighlight(boolean visibleHighlight) {
        this.visibleHighlight = visibleHighlight;
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