package fig;

import java.awt.*;

// 描画する図形を記録するクラスの抽象クラス
public abstract class Figure {
    protected int x, y, width, height;
    protected Color color;
    public Figure(int x, int y, int w, int h, Color c) {
        this.x = x; this.y = y;  // this.x, this.y はインスタンス変数．
        width = w; height = h;   // ローカル変数で同名の変数がある場合は，this
        color = c;               // を付けると，インスタンス変数を指す．
    }
    public void setSize(int w, int h) {
        width = w; height = h;
    }
    public void setLocation(int x, int y) {
        this.x = x; this.y = y;
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
}