package model;

import command.CommandEnum;
import event.CommandEvent;
import event.CommandListener;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class DrawModel implements CommandListener {
    protected final PaletteAndBrush paletteBrush;
    protected final Canvas canvas;
    protected final HashMap<CommandEnum, Consumer<CommandEvent>> commandProcessors;

    public DrawModel(PaletteAndBrush p, Canvas c) {
        paletteBrush = p;
        canvas = c;
        commandProcessors = createCommandProcessors();
    }

    protected HashMap<CommandEnum, Consumer<CommandEvent>> createCommandProcessors() {
        HashMap<CommandEnum, Consumer<CommandEvent>> commandProcessors = new HashMap<>();
        commandProcessors.put(
                CommandEnum.CREATE_FIGURE,
                (e) -> canvas.createFigure(paletteBrush.createFigure(e.getPoint1().x, e.getPoint1().y))
        );
        commandProcessors.put(
                CommandEnum.RESHAPE_FIGURE,
                (e) -> canvas.reshapeFigure(e.getPoint1().x, e.getPoint1().y, e.getPoint2().x, e.getPoint2().y)
        );
        commandProcessors.put(
                CommandEnum.SELECT,
                (e) -> canvas.select(e.getPoint1().x, e.getPoint1().y)
        );
        commandProcessors.put(
                CommandEnum.DESELECT,
                (e) -> canvas.deselect()
        );
        commandProcessors.put(
                CommandEnum.HIGHLIGHT_ON,
                (e) -> canvas.setVisibleHighlight(true)
        );
        commandProcessors.put(
                CommandEnum.HIGHLIGHT_OFF,
                (e) -> canvas.setVisibleHighlight(false)
        );
        commandProcessors.put(
                CommandEnum.DELETE,
                (e) -> canvas.delete()
        );
        commandProcessors.put(
                CommandEnum.UNDO,
                (e) -> canvas.undo()
        );
        commandProcessors.put(
                CommandEnum.REDO,
                (e) -> canvas.redo()
        );
        commandProcessors.put(
                CommandEnum.QUIT,
                (e) -> System.exit(0)
        );
        commandProcessors.put(
                CommandEnum.CHANGE_COLOR,
                (e) -> {
                    Function<Color, Color> f = e.getChangeColorFunction();
                    paletteBrush.changeBrushColor(
                            f.apply(paletteBrush.getBrushColor())
                    );
                }
        );
        commandProcessors.put(
                CommandEnum.PALETTE1,
                (e) -> paletteBrush.changeBrushColor(0)
        );
        commandProcessors.put(
                CommandEnum.PALETTE2,
                (e) -> paletteBrush.changeBrushColor(1)
        );
        commandProcessors.put(
                CommandEnum.PALETTE3,
                (e) -> paletteBrush.changeBrushColor(2)
        );
        commandProcessors.put(
                CommandEnum.RECT,
                (e) -> paletteBrush.setRectangle(false)
        );
        commandProcessors.put(
                CommandEnum.FILLED_RECT,
                (e) -> paletteBrush.setRectangle(true)
        );
        commandProcessors.put(
                CommandEnum.CIRCLE,
                (e) -> paletteBrush.setCircle(false)
        );
        commandProcessors.put(
                CommandEnum.FILLED_CIRCLE,
                (e) -> paletteBrush.setCircle(true)
        );
        return commandProcessors;
    }

    public void commandPerformed(CommandEvent e) {
        commandProcessors.get(e.getCommand()).accept(e);
    }
}