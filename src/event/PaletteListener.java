package event;

import java.util.EventListener;

public interface PaletteListener extends EventListener {
    void paletteUpdated(PaletteEvent e);
}