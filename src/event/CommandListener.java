package event;

import java.util.EventListener;

/**
 * Listen to Command perform event.
 * See {@link CommandEvent}, {@link model.DrawModel}, {@link controller.CommandController}
 */
public interface CommandListener extends EventListener {
    void commandPerformed(CommandEvent e);
}