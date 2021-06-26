package fig;

import java.awt.*;

/**
 * Super class of all drawable class.
 */
public abstract class Figure implements Cloneable {
    protected int x, y, width, height;
    protected Color color;

    /**
     * @param x is an upper left corner point parameter.
     * @param y is an upper left corner point parameter.
     * @param w is a width parameter.
     * @param h is a height parameter.
     * @param c is a color parameter.
     */
    public Figure(int x, int y, int w, int h, Color c) {
        this.x = x; this.y = y;
        width = w; height = h;
        color = c;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setSize(int w, int h) {
        width = w; height = h;
    }

    /**
     * (x, y) is an upper left corner point of Figure.
     */
    public void setLocation(int x, int y) {
        this.x = x; this.y = y;
    }
    public void setColor(Color c) {
        color = c;
    }

    /**
     * Judge (x, y) is in hit box of Figure.
     * @return true if (x, y) in Figure.
     */
    public boolean isInFigBox(int x, int y) {
        return getX() <= x && getY() <= y
                && x <= getX() + getWidth() && y <= getY() + getHeight();
    }

    /**
     * Reshape Figure to fit the box given by parameters.
     */
    public void reshape(int x1, int y1, int x2, int y2) {
        int newx = Math.min(x1, x2);
        int newy = Math.min(y1, y2);
        int neww = Math.abs(x1 - x2);
        int newh = Math.abs(y1 - y2);
        setLocation(newx, newy);
        setSize(neww, newh);
    }
    public abstract void draw(Graphics g);
    public abstract Figure clone();
}