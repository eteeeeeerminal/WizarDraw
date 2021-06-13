package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.function.IntConsumer;

public class CommandProcessor {
    protected enum ModeEnum {
        NORMAL(CommandProcessor::normalProcessor),
        FILE(CommandProcessor::fileProcessor),
        COLOR(CommandProcessor::colorProcessor);

        private IntConsumer processor;
        private ModeEnum(IntConsumer processor) {
            this.processor = processor;
        }
        public void processCommand(int keycode) {
            this.processor.accept(keycode);
        }
    }
    protected static ModeEnum mode = ModeEnum.NORMAL;
    protected static DrawModel model;
    public CommandProcessor(DrawModel m) {
        model = m;
    }

    public void processCommand(int keycode) {
        if (KeyEvent.CTRL_MASK == keycode) {
            // ショートカットを書く
        } else if (KeyEvent.VK_ESCAPE == keycode || KeyEvent.VK_Z == keycode) {
            mode = ModeEnum.NORMAL;
        } else {
            mode.processCommand(keycode);
        }
    }

    protected static void normalProcessor(int keycode) {
        if (KeyEvent.VK_F == keycode) {
            mode = ModeEnum.FILE;
        } else if (KeyEvent.VK_C == keycode) {
            mode = ModeEnum.COLOR;
        }
    }
    protected static void fileProcessor(int keyCode) {
        if (KeyEvent.VK_Q == keyCode) {
            System.exit(0);
        }
    }
    protected static void colorProcessor(int keycode) {
        if (KeyEvent.VK_C == keycode) {
            Color color = JColorChooser.showDialog(null, "色を選択", model.getCurrentColor());
            model.changeCurrentColor(color);
        } else if (KeyEvent.VK_1 == keycode) {
            model.changeCurrentColor(0);
        } else if (KeyEvent.VK_2 == keycode) {
            model.changeCurrentColor(1);
        } else if (KeyEvent.VK_3 == keycode) {
            model.changeCurrentColor(2);
        }
    }
}