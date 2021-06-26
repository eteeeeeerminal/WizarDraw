package event;

import java.util.EventListener;

/**
 * Listen to changes of {@link model.Canvas} object
 * See {@link view.CanvasView}
 */
public interface CanvasListener extends EventListener {
    /**
     * Any changes of {@link model.Canvas} object trigger canvasUpdated.
     * @param e
     */
    void canvasUpdated(CanvasEvent e);
}