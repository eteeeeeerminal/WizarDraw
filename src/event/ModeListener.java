package event;

import java.util.EventListener;

public interface ModeListener extends EventListener {
    void modeChanged(ModeEvent e);
}