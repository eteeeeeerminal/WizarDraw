package model;

import java.util.EventListener;

public interface DrawModelListener extends EventListener {
    void modelUpdated(DrawModelEvent e);
}