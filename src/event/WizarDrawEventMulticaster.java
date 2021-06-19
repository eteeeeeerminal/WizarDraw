package event;

import java.util.EventListener;

public class WizarDrawEventMulticaster
        implements PaletteListener, CanvasListener,
            CommandListener, ModeListener {
    protected final EventListener a,b;

    protected WizarDrawEventMulticaster(EventListener a, EventListener b) {
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
    public void commandPerformed(CommandEvent e) {
        ((CommandListener)a).commandPerformed(e);
        ((CommandListener)b).commandPerformed(e);
    }
    public void modeChanged(ModeEvent e) {
        ((ModeListener)a).modeChanged(e);
        ((ModeListener)b).modeChanged(e);
    }

    public static PaletteListener add(PaletteListener a, PaletteListener b) {
        return (PaletteListener)addInternal(a, b);
    }
    public static CanvasListener add(CanvasListener a, CanvasListener b) {
        return (CanvasListener)addInternal(a, b);
    }
    public static ModeListener add(ModeListener a, ModeListener b) {
        return (ModeListener)addInternal(a, b);
    }
    public static CommandListener add(CommandListener a, CommandListener b) {
        return (CommandListener)addInternal(a, b);
    }

    protected static EventListener addInternal(EventListener a, EventListener b) {
        if(a == null) return b;
        if(b == null) return a;
        return new WizarDrawEventMulticaster(a, b);
    }
}