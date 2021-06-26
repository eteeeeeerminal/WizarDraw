package command;

/**
 * Express command constants.
 * <p>
 *     See {@link model.DrawModel}, {@link controller.CommandController}, {@link view.CommandNavigator}, {@link event.CommandEvent}
 */
public enum CommandEnum {
    /**
     * Add a Figure specified by Brush to Canvas.
     */
    CREATE_FIGURE("CreateFigure", "MouseLB"),
    /**
     * Reshape a selected Figure in Canvas.
     */
    RESHAPE_FIGURE("ReshapeFigure", "MouseDrag"),
    /**
     * Select a pointed Figure.
     */
    SELECT("Select", "MouseLB"),
    /**
     * Deselect a selected Figure.
     */
    DESELECT("DeSelect", "q"),
    /**
     * Highlight a selected Figure.
     */
    HIGHLIGHT_ON("HighlightON", "ModeChange"),
    /**
     * Highlight off a selected Figure.
     */
    HIGHLIGHT_OFF("HighlightOFF", "ModeChange"),
    /**
     * Delete a selected Figure.
     */
    DELETE("Delete", "d"),
    /**
     * Undo latest {@link CommandEnum#CREATE_FIGURE}.
     */
    UNDO("Undo", "Ctrl+z"),
    /**
     * Redo latest {@link CommandEnum#UNDO}.
     */
    REDO("Redo", "Ctrl+x"),
    /**
     * Exit 0.
     */
    QUIT("Quit", "q"),
    /**
     * Change Color of the palette in use.
     */
    CHANGE_COLOR("ChangeColor", "c"),
    /**
     * Use palette1.
     */
    PALETTE1("Palette1", "1"),
    /**
     * Use palette1.
     */
    PALETTE2("Palette2", "2"),
    /**
     * Change the palette to palette1.
     */
    PALETTE3("Palette3", "3"),
    /**
     * Change Brush Figure to non-filled rect.
     */
    RECT("Rect", "r"),
    /**
     * Change Brush Figure to filled rect.
     */
    FILLED_RECT("FilledRect", "Shift+r"),
    /**
     * Change Brush Figure to non-filled circle.
     */
    CIRCLE("Circle", "c"),
    /**
     * Change Brush Figure to filled circle.
     */
    FILLED_CIRCLE("FilledCircle","Shift+c"),
    /**
     * Change Brush Figure to line.
     */
    LINE("Line", "s");

    /**
     * Command name.
     */
    public final String name;

    /**
     * Name of Key, Mouse input, or Event perform the command.
     * This field is for {@link view.CommandNavigator}.
     */
    public final String command;

    private CommandEnum(String name, String command) {
        this.name = name; this.command = command;
    }
}