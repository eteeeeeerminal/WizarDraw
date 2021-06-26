package event;

import java.util.EventListener;

/**
 * Listen to change of {@link model.PaletteAndBrush} object.
 * See {@link view.PaletteView}
 */
public interface PaletteListener extends EventListener {
    /**
     * Any changes of {@link model.PaletteAndBrush} object trigger paletteUpdated.
     * @param e
     */
    void paletteUpdated(PaletteEvent e);
}