package model;

import java.util.EventListener;

public interface DrawEventListener extends EventListener {
    void modelUpdated(DrawEvent e);
}