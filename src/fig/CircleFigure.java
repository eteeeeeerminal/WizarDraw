package fig;

import java.awt.*;

public class CircleFigure extends FillableFigure {
    public CircleFigure(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, c);
    }
    public CircleFigure(int x, int y, int w, int h, Color c, boolean isFilled) {
        super(x, y, w, h, c, isFilled);
    }
    public void draw(Graphics g) {
        g.setColor(color);
        if (isFilled) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }

    public Figure clone() {
        return new CircleFigure(x, y, width, height, color, isFilled);
    }
}