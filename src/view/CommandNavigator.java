package view;

import command.CommandEnum;
import command.ModeEnum;
import event.ModeEvent;
import event.ModeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.HashMap;

class Items extends JPanel {
    public Items(JComponent[] items) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        for(JComponent item: items) {
            item.setAlignmentX(0.0f);
            add(new Padding(item, 2));
        }
    }
}

class Padding extends JPanel {
    public Padding(JComponent element, int top, int left, int bottom, int right) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(top, left, bottom, right));
        add(element);
    }
    public Padding(JComponent element, int topBottom, int left, int right) {
        this(element, topBottom, left, topBottom, right);
    }
    public Padding(JComponent element, int topBottom, int leftRight) {
        this(element, topBottom, leftRight, leftRight);
    }
    public Padding(JComponent element, int padding) {
        this(element, padding, padding);
    }
}

class Div extends JPanel {
    public Div(JLabel header, JPanel body) {
        setLayout(new BorderLayout());
        add(new Padding(header, 3), BorderLayout.NORTH);
        add(new Padding(body, 0, 10, 0), BorderLayout.CENTER);
        deactivate();
    }
    public void activate() {
        setBorder(new EtchedBorder(Color.RED, Color.RED));
    }
    public void deactivate() {
        setBorder(new EtchedBorder(new Color(0, 0, 0, 0), Color.BLACK));
    }
}

public class CommandNavigator extends JPanel implements ModeListener {
    protected HashMap<ModeEnum, Div> modeNavElements = new HashMap<>();
    public CommandNavigator() {
        setLayout(new BorderLayout());

        Div fileMode = new Div(
                makeLabel(ModeEnum.FILE),
                new Items(new JComponent[]{
                        makeLabel(CommandEnum.QUIT),
                })
        );
        Div colorMode = new Div(
                makeLabel(ModeEnum.COLOR),
                new Items(new JComponent[]{
                        makeLabel(CommandEnum.CHANGE_COLOR),
                        makeLabel(CommandEnum.PALETTE1),
                        makeLabel(CommandEnum.PALETTE2),
                        makeLabel(CommandEnum.PALETTE3),
                })
        );
        Div brushMode = new Div(
                makeLabel(ModeEnum.BRUSH),
                new Items(new JComponent[]{
                        makeLabel(CommandEnum.RECT),
                        makeLabel(CommandEnum.FILLED_RECT),
                        makeLabel(CommandEnum.CIRCLE),
                        makeLabel(CommandEnum.FILLED_CIRCLE),
                })
        );
        Div selectMode = new Div(
                makeLabel(ModeEnum.SELECT),
                new Items(new JComponent[]{
                        makeLabel(CommandEnum.DELETE),
                        makeLabel(CommandEnum.DESELECT),
                })
        );
        Div normalMode = new Div(
                makeLabel(ModeEnum.NORMAL),
                new Items(new JComponent[]{
                        fileMode,
                        colorMode,
                        brushMode,
                        selectMode,
                })
        );
        normalMode.activate();
        add(new Padding(normalMode, 3), BorderLayout.CENTER);

        Div rootCommands = new Div(
                new JLabel("Root commands"),
                new Items(new JComponent[]{
                        makeLabel(ModeEnum.NORMAL),
                        makeLabel(CommandEnum.UNDO),
                        makeLabel(CommandEnum.REDO),
                })
        );
        add(new Padding(rootCommands, 3), BorderLayout.SOUTH);

        modeNavElements.put(ModeEnum.NORMAL, normalMode);
        modeNavElements.put(ModeEnum.FILE, fileMode);
        modeNavElements.put(ModeEnum.COLOR, colorMode);
        modeNavElements.put(ModeEnum.BRUSH, brushMode);
        modeNavElements.put(ModeEnum.SELECT, selectMode);
    }

    public void modeChanged(ModeEvent e) {
        modeNavElements.get(e.getPreviousMode()).deactivate();
        modeNavElements.get(e.getLatestMode()).activate();
    }

    public static JLabel makeLabel(ModeEnum mode) {
        return new JLabel(String.format("%s: [ %s ]", mode.command, mode.name));
    }
    public static JLabel makeLabel(CommandEnum command) {
        return new JLabel(String.format("%s: [ %s ]", command.command, command.name));
    }
}