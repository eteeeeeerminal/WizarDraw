package controller;

import command.CommandEnum;
import command.ModeEnum;
import event.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandController implements KeyListener {
    protected ModeEnum mode = ModeEnum.NORMAL;
    protected CommandListener cmdListener;
    protected ModeListener modeListener;

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        processCommand(e.getKeyCode(), e.getModifiersEx());
    }
    public void keyReleased(KeyEvent e) {}

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
                case BRUSH:
                    brushProcessor(keycode, modifiers);
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
            modeChange(ModeEnum.BRUSH);
        }
    }
    protected void fileProcessor(int keyCode, int modifiers) {
        if (KeyEvent.VK_Q == keyCode) {
            commandPerform(CommandEnum.QUIT);
        }
    }
    protected void colorProcessor(int keycode, int modifiers) {
        if (KeyEvent.VK_C == keycode) {
            commandPerform(CommandEnum.CHANGE_COLOR);
        } else if (KeyEvent.VK_1 == keycode) {
            commandPerform(CommandEnum.PALETTE1);
        } else if (KeyEvent.VK_2 == keycode) {
            commandPerform(CommandEnum.PALETTE2);
        } else if (KeyEvent.VK_3 == keycode) {
            commandPerform(CommandEnum.PALETTE3);
        }
    }
    protected void brushProcessor(int keycode, int modifiers) {
        if ((KeyEvent.SHIFT_DOWN_MASK & modifiers) == KeyEvent.SHIFT_DOWN_MASK) {
            if (KeyEvent.VK_R == keycode) {
                commandPerform(CommandEnum.FILLED_RECT);
            }
        } else {
            if (KeyEvent.VK_R == keycode) {
                commandPerform(CommandEnum.RECT);
            }
        }
    }
    public static Color showColorDialog(Color c) {
        return JColorChooser.showDialog(null, "色を選択", c);
    }
    public void addCommandListener(CommandListener l) {
        if (l == null) {
            return;
        }
        cmdListener = WizarDrawEventMulticaster.add(cmdListener, l);
    }
    public void commandPerform(CommandEnum command) {
        if (cmdListener == null) {
            return;
        }
        if (command == CommandEnum.CHANGE_COLOR) {
            cmdListener.commandPerformed(
                    new CommandEvent(this, command, CommandController::showColorDialog)
            );
        } else {
            cmdListener.commandPerformed(new CommandEvent(this, command));
        }
    }
    public void addModeListener(ModeListener l) {
        if (l == null) {
            return;
        }
        modeListener = WizarDrawEventMulticaster.add(modeListener, l);
    }
    public void modeChange(ModeEnum m) {
        mode = m;
        if (modeListener != null) {
            modeListener.modeChanged(new ModeEvent(this));
        }
    }
}