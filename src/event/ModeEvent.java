package event;

import command.ModeEnum;
import controller.CommandController;

import java.util.EventObject;

/**
 * Used in ModeListener.
 * See {@link ModeListener}, {@link controller.CommandController}
 */
public class ModeEvent extends EventObject {
    protected ModeEnum latest;
    protected ModeEnum previous;
    public ModeEvent(CommandController source, ModeEnum latest, ModeEnum previous) {
        super(source);
        this.latest = latest;
        this.previous = previous;
    }

    /**
     * @return Current mode. After changed mode.
     */
    public ModeEnum getLatestMode() {
        return latest;
    }

    /**
     * @return Previous mode. Before changed mode.
     */
    public ModeEnum getPreviousMode() {
        return previous;
    }
}