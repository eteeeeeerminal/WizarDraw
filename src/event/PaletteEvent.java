package event;

import model.PaletteAndBrush;

import java.util.EventObject;

public class PaletteEvent extends EventObject {
    public PaletteEvent(PaletteAndBrush source) {
        super(source);
    }
}