package controller;

import command.CommandEnum;
import command.ModeEnum;
import event.*;

import javax.swing.*;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Listen Key and Mouse Event. Fire Command and Mode Event.
 * <p>
 *     See {@link command.CommandEnum}, {@link command.ModeEnum}, {@link event.CommandEvent}, {@link event.ModeEvent}
 */
public class CommandController
        implements KeyListener, MouseListener, MouseMotionListener {
    protected ModeEnum mode = ModeEnum.NORMAL;
    protected CommandListener cmdListener;
    protected ModeListener modeListener;
    // This is used for reshape commands.
    protected final Point mouseStart = new Point();

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        processCommand(e.getKeyCode(), e.getModifiersEx());
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        mouseStart.setLocation(e.getPoint());
        if (ModeEnum.SELECT == mode) {
            commandPerform(new CommandEvent(this, CommandEnum.SELECT, e.getPoint()));
        }else {
            commandPerform(new CommandEvent(this, CommandEnum.CREATE_FIGURE, e.getPoint()));
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (ModeEnum.SELECT == mode) {
            // will add some commands
        } else {
            commandPerform(new CommandEvent(this, CommandEnum.RESHAPE_FIGURE, mouseStart, e.getPoint()));
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}

    /**
     * Perform CommandEvent for keyboard input.
     * @param keycode is defined in {@link KeyEvent}
     * @param modifiers Key modifiers are defined in {@link KeyEvent}
     */
    public void processCommand(int keycode, int modifiers) {
        if ((KeyEvent.CTRL_DOWN_MASK & modifiers) == KeyEvent.CTRL_DOWN_MASK) {
            // root commands
            if (KeyEvent.VK_Z == keycode) {
                simpleCommandPerform(CommandEnum.UNDO);
            } else if (KeyEvent.VK_X == keycode) {
                simpleCommandPerform(CommandEnum.REDO);
            }
        } else if (KeyEvent.VK_ESCAPE == keycode || KeyEvent.VK_Z == keycode) {
            modeChange(ModeEnum.NORMAL);
        } else {
            // commands specified by mode
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
                case SELECT:
                    selectProcessor(keycode, modifiers);
                    break;
            }
        }
    }

    /**
     * Process commands in normal mode.
     * @param keycode
     * @param modifiers
     */
    protected void normalProcessor(int keycode, int modifiers) {
        if (KeyEvent.VK_F == keycode) {
            modeChange(ModeEnum.FILE);
        } else if (KeyEvent.VK_C == keycode) {
            modeChange(ModeEnum.COLOR);
        }else if (KeyEvent.VK_B == keycode) {
            modeChange(ModeEnum.BRUSH);
        } else if (KeyEvent.VK_S == keycode) {
            modeChange(ModeEnum.SELECT);
            simpleCommandPerform(CommandEnum.HIGHLIGHT_ON);
        }
    }

    /**
     * Process commands in file mode.
     * @param keyCode
     * @param modifiers
     */
    protected void fileProcessor(int keyCode, int modifiers) {
        if (KeyEvent.VK_Q == keyCode) {
            simpleCommandPerform(CommandEnum.QUIT);
        }
    }

    /**
     * Process commands in color mode.
     * @param keycode
     * @param modifiers
     */
    protected void colorProcessor(int keycode, int modifiers) {
        if (KeyEvent.VK_C == keycode) {
            colorChangePerform(CommandEnum.CHANGE_COLOR);
        } else if (KeyEvent.VK_1 == keycode) {
            simpleCommandPerform(CommandEnum.PALETTE1);
        } else if (KeyEvent.VK_2 == keycode) {
            simpleCommandPerform(CommandEnum.PALETTE2);
        } else if (KeyEvent.VK_3 == keycode) {
            simpleCommandPerform(CommandEnum.PALETTE3);
        }
    }

    /**
     * Process commands in brush mode.
     * @param keycode
     * @param modifiers
     */
    protected void brushProcessor(int keycode, int modifiers) {
        if ((KeyEvent.SHIFT_DOWN_MASK & modifiers) == KeyEvent.SHIFT_DOWN_MASK) {
            if (KeyEvent.VK_R == keycode) {
                simpleCommandPerform(CommandEnum.FILLED_RECT);
            } else if (KeyEvent.VK_C == keycode) {
                simpleCommandPerform(CommandEnum.FILLED_CIRCLE);
            }
        } else {
            if (KeyEvent.VK_R == keycode) {
                simpleCommandPerform(CommandEnum.RECT);
            } else if (KeyEvent.VK_C == keycode) {
                simpleCommandPerform(CommandEnum.CIRCLE);
            } else if (KeyEvent.VK_S == keycode) {
                simpleCommandPerform(CommandEnum.LINE);
            }
        }
    }

    /**
     * Process commands in select mode.
     * @param keycode
     * @param modifiers
     */
    protected void selectProcessor(int keycode, int modifiers) {
        if (KeyEvent.VK_D == keycode) {
            simpleCommandPerform(CommandEnum.DELETE);
        } else if (KeyEvent.VK_Q == keycode) {
            simpleCommandPerform(CommandEnum.DESELECT);
        }
    }

    public void addCommandListener(CommandListener l) {
        if (l == null) {
            return;
        }
        cmdListener = WizarDrawEventMulticaster.add(cmdListener, l);
    }

    protected void commandPerform(CommandEvent e) {
        if (cmdListener == null) {
            return;
        }
        cmdListener.commandPerformed(e);
    }

    public void colorChangePerform(CommandEnum command) {
        commandPerform(new CommandEvent(
                this, command,
                (c) -> (JColorChooser.showDialog(null, "Choose Color", c))
        ));
    }

    /**
     * Perform command with no arguments.
     * @param command
     */
    public void simpleCommandPerform(CommandEnum command) {
        commandPerform(new CommandEvent(this, command));
    }

    public void addModeListener(ModeListener l) {
        if (l == null) {
            return;
        }
        modeListener = WizarDrawEventMulticaster.add(modeListener, l);
    }

    public void modeChange(ModeEnum m) {
        simpleCommandPerform(CommandEnum.HIGHLIGHT_OFF);
        ModeEnum previous = mode;
        mode = m;
        if (modeListener != null) {
            modeListener.modeChanged(new ModeEvent(this, mode, previous));
        }
    }
}