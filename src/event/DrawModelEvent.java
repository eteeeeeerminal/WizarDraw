package event;

import model.DrawModel;

import java.util.EventObject;

public class DrawModelEvent extends EventObject {
    public DrawModelEvent(DrawModel source) {
        super(source);
    }
}