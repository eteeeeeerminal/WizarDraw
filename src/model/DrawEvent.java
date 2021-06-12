package model;

import java.util.EventObject;

public class DrawEvent extends EventObject {
    public DrawEvent(DrawModel source) {
        super(source);
    }
}