package model;

import java.util.EventObject;

public class DrawModelEvent extends EventObject {
    public DrawModelEvent(DrawModel source) {
        super(source);
    }
}