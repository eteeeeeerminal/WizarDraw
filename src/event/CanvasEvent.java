package event;

import model.Canvas;

import java.util.EventObject;

public class CanvasEvent extends EventObject {
    public CanvasEvent(Canvas source) {
        super(source);
    }
}
