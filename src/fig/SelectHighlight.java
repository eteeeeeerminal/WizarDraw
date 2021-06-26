package fig;

import java.awt.*;

/**
 * Used to indicate which Figure is selected.
 * See {@link model.Canvas}
 */
public class SelectHighlight extends Figure {
    protected Color outlineColor;
    public SelectHighlight(int x, int y, int w, int h) {
        super(x, y, w, h, new Color(0,128,255, 100));
        outlineColor = new Color(255,128,0, 100);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(outlineColor);
        g.drawRect(x, y, width, height);
    }

    public Figure clone() {
        return new SelectHighlight(x, y, width, height);
    }
}