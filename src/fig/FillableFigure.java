package fig;

import java.awt.*;

/**
 * Super class of Figure can be filled in.
 */
public abstract class FillableFigure extends Figure {
    protected boolean isFilled = false;
    public FillableFigure(int x, int y, int w, int h, Color c, boolean isFilled) {
        super(x, y, w, h, c);
        this.isFilled = isFilled;
    }
    public FillableFigure(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, c);
    }
}