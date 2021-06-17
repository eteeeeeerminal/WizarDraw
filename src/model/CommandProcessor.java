package model;

import event.CommandEvent;
import event.CommandListener;
import event.ModelEventMulticaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class CommandProcessor {
    public enum ModeEnum {
        NORMAL,
        FILE,
        COLOR,
        DRAW_TOOL,
    }
    protected ModeEnum mode = ModeEnum.NORMAL;
    protected final DrawModel model;
    protected CommandListener listener;

    public CommandProcessor(DrawModel m) {
        model = m;
    }
    public ModeEnum getMode() {
        return mode;
    }

    public void processCommand(int keycode, int modifiers) {
        if ((KeyEvent.CTRL_DOWN_MASK & modifiers) == KeyEvent.CTRL_DOWN_MASK) {
            // ショートカットを書く
        } else if (KeyEvent.VK_ESCAPE == keycode || KeyEvent.VK_Z == keycode) {
            modeChange(ModeEnum.NORMAL);
        } else {
            switch (mode) {
                case NORMAL:
                    normalProcessor(keycode, modifiers);
                    break;
                case COLOR:
                    colorProcessor(keycode, modifiers);
                    break;
                case DRAW_TOOL:
                    drawToolProcessor(keycode, modifiers);
                    break;
                case FILE:
                    fileProcessor(keycode, modifiers);
                    break;
            }
        }
    }

    protected void normalProcessor(int keycode, int modifiers) {
        if (KeyEvent.VK_F == keycode) {
            modeChange(ModeEnum.FILE);
        } else if (KeyEvent.VK_C == keycode) {
            modeChange(ModeEnum.COLOR);
        } else if (KeyEvent.VK_S == keycode) {
            modeChange(ModeEnum.DRAW_TOOL);
        }
    }
    protected void fileProcessor(int keyCode, int modifiers) {
        if (KeyEvent.VK_Q == keyCode) {
            System.exit(0);
        }
    }
    protected void colorProcessor(int keycode, int modifiers) {
        if (KeyEvent.VK_C == keycode) {
            Color color = JColorChooser.showDialog(null, "色を選択", model.getCurrentColor());
            if (color != null){
                model.changeCurrentColor(color);
            }
        } else if (KeyEvent.VK_1 == keycode) {
            model.changeCurrentColor(0);
        } else if (KeyEvent.VK_2 == keycode) {
            model.changeCurrentColor(1);
        } else if (KeyEvent.VK_3 == keycode) {
            model.changeCurrentColor(2);
        }
    }
    protected void drawToolProcessor(int keycode, int modifiers) {
        if ((KeyEvent.SHIFT_DOWN_MASK & modifiers) == KeyEvent.SHIFT_DOWN_MASK) {
            if (KeyEvent.VK_R == keycode) {
                model.setRectangle(true);
            }
        } else {
            if (KeyEvent.VK_R == keycode) {
                model.setRectangle(false);
            }
        }
    }
    public void addListener(CommandListener l) {
        if (l == null) {
            return;
        }
        listener = ModelEventMulticaster.add(listener, l);
    }
    public void modeChange(ModeEnum m) {
        mode = m;
        if (listener != null) {
            listener.modeChanged(new CommandEvent(this));
        }
    }
}