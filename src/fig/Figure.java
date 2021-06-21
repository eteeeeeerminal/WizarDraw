package fig;

import java.awt.*;

// 描画する図形を記録するクラスの抽象クラス
public abstract class Figure implements Cloneable {
    protected int x, y, width, height;
    protected Color color;
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
    public void setLocation(int x, int y) {
        this.x = x; this.y = y;
    }
    public void setColor(Color c) {
        color = c;
    }
    public boolean isInFigBox(int x, int y) {
        return getX() <= x && getY() <= y
                && x <= getX() + getWidth() && y <= getY() + getHeight();
    }
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