package event;

import model.Canvas;

import java.util.EventObject;

/**
 * This class doesn't have any roles yet.
 * See {@link CanvasListener}
 */
public class CanvasEvent extends EventObject {
    public CanvasEvent(Canvas source) {
        super(source);
    }
}
