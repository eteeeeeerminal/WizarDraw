package model;

import event.WizarDrawEventMulticaster;
import event.PaletteEvent;
import event.PaletteListener;
import fig.CircleFigure;
import fig.Figure;
import fig.RectangleFigure;

import java.awt.*;

public class PaletteAndBrush {
    public static final int COLOR_PALETTE_SIZE=3;
    protected Figure brush; // command processorのところでやったようなテクニックでコンストラクタをgetのときに呼ぶようにする
    protected Color[] palette = new Color[COLOR_PALETTE_SIZE];
    protected int currentColor;
    protected PaletteListener listener;

    public PaletteAndBrush() {
        palette[0] = Color.RED;
        palette[1] = Color.GREEN;
        palette[2] = Color.BLUE;
        currentColor = 0;
        listener = null;
        setRectangle(false);
    }
    public void setRectangle(boolean isFilled) {
        brush = new RectangleFigure(0,0,0,0, getBrushColor(), isFilled);
        update();
    }
    public void setCircle(boolean isFilled) {
        brush = new CircleFigure(0,0,0,0, getBrushColor(), isFilled);
        update();
    }
    public Figure createFigure(int x, int y) {
        brush.setColor(getBrushColor());
        brush.setLocation(x, y);
        return brush.clone();
    }
    public Color[] getPalette() {
        return palette;
    }
    public Color getBrushColor() {
        return palette[currentColor];
    }
    public void changeBrushColor(int paletteNum) {
        if(paletteNum < 0 || paletteNum >= COLOR_PALETTE_SIZE) {
            throw new Error("Invalid paletteNum");
        }
        currentColor = paletteNum;
        update();
    }
    public void changeBrushColor(Color c) {
        if (c == null) {
            return;
        }
        palette[currentColor] = c;
        update();
    }
    public void addListener(PaletteListener l) {
        if (l == null) {
            return;
        }
        listener = WizarDrawEventMulticaster.add(listener, l);
    }
    protected void update() {
        if (listener != null) {
            listener.paletteUpdated(new PaletteEvent(this));
        }
    }
}