package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

public class CommandProcessor {
    protected enum ModeEnum {
        NORMAL,
        FILE,
    }
    protected static ModeEnum mode = ModeEnum.NORMAL;
    // switchよりもmapを使った方が分岐の自由度が下がるため見通しが多少楽になる
    protected static final Map<ModeEnum, IntConsumer> processors = new HashMap<ModeEnum, IntConsumer>() {
        {
            put(ModeEnum.NORMAL, CommandProcessor::normalProcessor);
            put(ModeEnum.FILE, CommandProcessor::fileProcessor);
        }
    };
    protected static DrawModel model;
    public CommandProcessor(DrawModel m) {
        model = m;
    }
    public void processCommand(int keycode) {
        processors.get(mode).accept(keycode);
    }
    protected static void normalProcessor(int keycode) {
        if (KeyEvent.VK_F == keycode) {
            mode = ModeEnum.FILE;
        } else if (KeyEvent.VK_C == keycode) {
            Color color = JColorChooser.showDialog(null, "色を選択", new Color(0, 0, 0));
            model.changeColor(color);
        }
    }
    protected static void fileProcessor(int keyCode) {
        if (KeyEvent.VK_Q == keyCode) {
            System.exit(0);
        }
    }
}