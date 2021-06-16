package event;

import java.util.EventListener;

public class ModelEventMulticaster
        implements DrawModelListener, PaletteListener {
    protected final EventListener a,b;

    protected ModelEventMulticaster(EventListener a, EventListener b) {
        this.a = a; this.b = b;
    }

    public void canvasUpdated(DrawModelEvent e) {
        ((DrawModelListener)a).canvasUpdated(e);
        ((DrawModelListener)b).canvasUpdated(e);
    }
    public void paletteUpdated(PaletteEvent e) {
        ((PaletteListener)a).paletteUpdated(e);
        ((PaletteListener)b).paletteUpdated(e);
    }

    public static DrawModelListener add(DrawModelListener a, DrawModelListener b) {
        return (DrawModelListener)addInternal(a, b);
    }
    public static PaletteListener add(PaletteListener a, PaletteListener b) {
        return (PaletteListener)addInternal(a, b);
    }

    protected static EventListener addInternal(EventListener a, EventListener b) {
        if(a == null) return b;
        if(b == null) return a;
        return new ModelEventMulticaster(a, b);
    }
}