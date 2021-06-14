package model;

import java.awt.*;
import java.util.ArrayList;

import fig.Figure;
import fig.RectangleFigure;

////////////////////////////////////////////////
// Model (M)
public class DrawModel {
    public static final int COLOR_PALETTE_SIZE=3;
    protected ArrayList<Figure> fig;
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
    }
    public ArrayList<Figure> getFigures() {
        return fig;
    }
    public Figure getFigure(int idx) {
        return fig.get(idx);
    }
    public void createFigure(int x, int y) {
        Figure f = new RectangleFigure(x, y, 0, 0, palette[currentColor]);
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
    }
    public void changeCurrentColor(Color c) {
        palette[currentColor] = c;
    }

    public void addListener(DrawModelListener listener) {
        this.listener = listener;
    }
    public void update() {
        listener.modelUpdated(new DrawModelEvent(this));
    }
}