package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.BiConsumer;

public class CommandProcessor {
    public enum ModeEnum {
        NORMAL(CommandProcessor::normalProcessor),
        FILE(CommandProcessor::fileProcessor),
        COLOR(CommandProcessor::colorProcessor),
        DRAW_TOOL(CommandProcessor::drawToolProcessor);

        private BiConsumer<Integer, Integer> processor;
        private ModeEnum(BiConsumer<Integer, Integer> processor) {
            this.processor = processor;
        }
        public void processCommand(int keycode, int modifiers) {
            this.processor.accept(keycode, modifiers);
        }
    }
    protected static ModeEnum mode = ModeEnum.NORMAL;
    protected static DrawModel model;
    public CommandProcessor(DrawModel m) {
        model = m;
    }

    public void processCommand(int keycode, int modifiers) {
        if ((KeyEvent.CTRL_DOWN_MASK & modifiers) == KeyEvent.CTRL_DOWN_MASK) {
            // ショートカットを書く
        } else if (KeyEvent.VK_ESCAPE == keycode || KeyEvent.VK_Z == keycode) {
            mode = ModeEnum.NORMAL;
        } else {
            mode.processCommand(keycode, modifiers);
        }
    }

    protected static void normalProcessor(int keycode, int modifiers) {
        if (KeyEvent.VK_F == keycode) {
            mode = ModeEnum.FILE;
        } else if (KeyEvent.VK_C == keycode) {
            mode = ModeEnum.COLOR;
        } else if (KeyEvent.VK_S == keycode) {
            mode = ModeEnum.DRAW_TOOL;
        }
    }
    protected static void fileProcessor(int keyCode, int modifiers) {
        if (KeyEvent.VK_Q == keyCode) {
            System.exit(0);
        }
    }
    protected static void colorProcessor(int keycode, int modifiers) {
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
    protected static void drawToolProcessor(int keycode, int modifiers) {
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
}