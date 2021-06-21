package command;

public enum CommandEnum {
    CREATE_FIGURE("CreateFigure", "MouseLB"),
    RESHAPE_FIGURE("ReshapeFigure", "MouseDrag"),
    SELECT("Select", "MouseLB"),
    DESELECT("DeSelect", "q"),
    HIGHLIGHT_ON("HighlightON", "ModeChange"),
    HIGHLIGHT_OFF("HighlightOFF", "ModeChange"),
    DELETE("Delete", "d"),
    UNDO("Undo", "Ctrl+z"),
    REDO("Redo", "Ctrl+x"),
    QUIT("Quit", "q"),
    CHANGE_COLOR("ChangeColor", "c"),
    PALETTE1("Palette1", "1"),
    PALETTE2("Palette2", "2"),
    PALETTE3("Palette3", "3"),
    RECT("Rect", "r"),
    FILLED_RECT("FilledRect", "Shift+r");

    public final String name;
    public final String command;
    private CommandEnum(String name, String command) {
        this.name = name; this.command = command;
    }
}