package event;

import java.util.EventListener;

/**
 * Listen to change mode of {@link controller.CommandController}
 * See {@link ModeEvent}, {@link controller.CommandController}
 */
public interface ModeListener extends EventListener {
    void modeChanged(ModeEvent e);
}