package event;

import java.util.EventListener;

public interface DrawModelListener extends EventListener {
    void canvasUpdated(DrawModelEvent e);
    void paletteUpdated(DrawModelEvent e);
}