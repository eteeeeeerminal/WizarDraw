package event;

import java.util.EventListener;

public interface CommandListener extends EventListener {
    void commandPerformed(CommandEvent e);
}