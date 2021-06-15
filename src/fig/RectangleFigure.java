package fig;

import java.awt.*;

public class RectangleFigure extends FillableFigure {
    public RectangleFigure(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, c);
    }
    public RectangleFigure(int x, int y, int w, int h, Color c, boolean isFilled) {
        super(x, y, w, h, c, isFilled);
    }
    public void draw(Graphics g) {
        g.setColor(color);
        if (isFilled) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}