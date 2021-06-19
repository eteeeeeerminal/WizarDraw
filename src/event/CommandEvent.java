package event;

import command.CommandEnum;
import controller.CommandController;

import java.awt.*;
import java.util.EventObject;
import java.util.function.Function;

public class CommandEvent extends EventObject {
    protected CommandEnum command;
    protected Function<Color, Color> changeColorF;
    public CommandEvent(CommandController source, CommandEnum c, Function<Color, Color> f) {
        super(source);
        command = c;
        changeColorF = f;
        if (command == CommandEnum.CHANGE_COLOR && f == null) {
            throw new Error("Change color command needs function");
        }
    }
    public CommandEvent(CommandController source, CommandEnum c) {
        this(source, c, null);
    }
    public CommandEnum getCommand() {
        return command;
    }
    public Function<Color, Color> getChangeColorFunction() {
        return changeColorF;
    }
}