package event;

import java.util.EventListener;

public interface CanvasListener extends EventListener {
    void canvasUpdated(CanvasEvent e);
}