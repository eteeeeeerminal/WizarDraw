package event;

import command.CommandEnum;
import controller.CommandController;

import java.awt.*;
import java.util.EventObject;
import java.util.function.Function;

public class CommandEvent extends EventObject {
    protected final CommandEnum command;
    protected final Point p1, p2;
    protected final Function<Color, Color> changeColorF;

    public CommandEvent(CommandController source, CommandEnum c, Point p1, Point p2) {
        super(source);
        command = c;
        changeColorF = null;
        this.p1 = p1; this.p2 = p2;
        validCommand();
    }
    public CommandEvent(CommandController source, CommandEnum c, Point p1) {
        this(source, c, p1, null);
    }
    public CommandEvent(CommandController source, CommandEnum c, Function<Color, Color> f) {
        super(source);
        command = c;
        changeColorF = f;
        p1 = null; p2 = null;
        validCommand();
    }
    public CommandEvent(CommandController source, CommandEnum c) {
        this(source, c, (Function<Color, Color>) null);
    }
    protected void validCommand() throws Error {
        if (command == CommandEnum.CHANGE_COLOR && changeColorF == null) {
            throw new Error("Change color command needs function");
        }
        if (command == CommandEnum.CREATE_FIGURE && p1 == null) {
            throw new Error("Create figure command needs a point");
        }
        if (command == CommandEnum.RESHAPE_FIGURE && (p1 == null || p2 == null)) {
            throw new Error("Reshape figure command needs two points");
        }
    }
    public CommandEnum getCommand() {
        return command;
    }
    public Point getPoint1() {
        return p1;
    }
    public Point getPoint2() {
        return p2;
    }
    public Function<Color, Color> getChangeColorFunction() {
        return changeColorF;
    }
}