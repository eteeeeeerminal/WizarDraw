package event;

import model.PaletteAndBrush;

import java.util.EventObject;

/**
 * Used in PaletteListener. This class doesn't have any roles yet.
 * See {@link PaletteListener}
 */
public class PaletteEvent extends EventObject {
    public PaletteEvent(PaletteAndBrush source) {
        super(source);
    }
}