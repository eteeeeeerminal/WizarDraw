package event;

import model.CommandProcessor;

import java.util.EventObject;

public class CommandEvent extends EventObject {
    public CommandEvent(CommandProcessor source) {
        super(source);
    }
}