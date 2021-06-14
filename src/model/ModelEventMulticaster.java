package model;

import java.util.EventListener;

public class ModelEventMulticaster implements DrawModelListener {
    protected final EventListener a,b;

    protected ModelEventMulticaster(EventListener a, EventListener b) {
        this.a = a; this.b = b;
    }

    public void modelUpdated(DrawModelEvent e) {
        ((DrawModelListener)a).modelUpdated(e);
        ((DrawModelListener)b).modelUpdated(e);
    }

    public static DrawModelListener add(DrawModelListener a, DrawModelListener b) {
        return (DrawModelListener)addInternal(a, b);
    }

    protected static EventListener addInternal(EventListener a, EventListener b) {
        if(a == null) return b;
        if(b == null) return a;
        return new ModelEventMulticaster(a, b);
    }
}