package command;

public enum ModeEnum {
    NORMAL("Normal", "ESC|z"),
    FILE("File", "f"),
    COLOR("Color", "c"),
    BRUSH("Brush", "b"),
    SELECT("Select", "s");

    public final String name;
    public final String command;
    private ModeEnum(String name, String command) {
        this.name = name; this.command = command;
    }
}