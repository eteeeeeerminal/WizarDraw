package event;

import controller.CommandController;

import java.util.EventObject;

public class ModeEvent extends EventObject {
    public ModeEvent(CommandController source) {
        super(source);
    }
}