package event;

import command.ModeEnum;
import controller.CommandController;

import java.util.EventObject;

public class ModeEvent extends EventObject {
    protected ModeEnum latest;
    protected ModeEnum previous;
    public ModeEvent(CommandController source, ModeEnum latest, ModeEnum previous) {
        super(source);
        this.latest = latest;
        this.previous = previous;
    }

    public ModeEnum getLatestMode() {
        return latest;
    }
    public ModeEnum getPreviousMode() {
        return previous;
    }
}