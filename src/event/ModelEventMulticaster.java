package event;

import java.util.EventListener;

public class ModelEventMulticaster
        implements PaletteListener, CanvasListener {
    protected final EventListener a,b;

    protected ModelEventMulticaster(EventListener a, EventListener b) {
        this.a = a; this.b = b;
    }

    public void canvasUpdated(CanvasEvent e) {
        ((CanvasListener)a).canvasUpdated(e);
        ((CanvasListener)b).canvasUpdated(e);
    }
    public void paletteUpdated(PaletteEvent e) {
        ((PaletteListener)a).paletteUpdated(e);
        ((PaletteListener)b).paletteUpdated(e);
    }

    public static PaletteListener add(PaletteListener a, PaletteListener b) {
        return (PaletteListener)addInternal(a, b);
    }
    public static CanvasListener add(CanvasListener a, CanvasListener b) {
        return (CanvasListener)addInternal(a, b);
    }

    protected static EventListener addInternal(EventListener a, EventListener b) {
        if(a == null) return b;
        if(b == null) return a;
        return new ModelEventMulticaster(a, b);
    }
}