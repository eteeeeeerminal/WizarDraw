package fig;

import java.awt.*;

public class LineFigure extends Figure {
    public LineFigure(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, c);
    }
    public int getX() {
        return (width > 0) ? x : x+width;
    }
    public int getY() {
        return (height > 0) ? y : y+height;
    }
    public int getWidth() {
        return Math.abs(width);
    }
    public int getHeight() {
        return Math.abs(height);
    }
    public void reshape(int x1, int y1, int x2, int y2) {
        setLocation(x1, y1);
        setSize(x2-x1, y2-y1);
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x+width, y+height);
    }
    public LineFigure clone() {
        return new LineFigure(x, y, width, height, color);
    }
}