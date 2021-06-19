package command;

public enum CommandEnum {
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