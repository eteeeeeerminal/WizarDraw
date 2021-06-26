package command;

/**
 * Express {@link controller.CommandController} mode constants.
 * <p>
 *     See {@link controller.CommandController}, {@link view.CommandNavigator}, {@link event.ModeEvent}
 */
public enum ModeEnum {
    /**
     * Only for switching other modes.
     */
    NORMAL("Normal", "ESC|z"),
    /**
     * System exit and File related commands.
     */
    FILE("File", "f"),
    /**
     * Operate Color and Palettes.
     */
    COLOR("Color", "c"),
    /**
     * Select Brush Figure.
     */
    BRUSH("Brush", "b"),
    /**
     * Select Figure, and then, operate selected Figure.
     */
    SELECT("Select", "s");

    /**
     * Mode name.
     */
    public final String name;

    /**
     * Name of Key activate the mode.
     * This field is for {@link view.CommandNavigator}.
     */
    public final String command;
    private ModeEnum(String name, String command) {
        this.name = name; this.command = command;
    }
}