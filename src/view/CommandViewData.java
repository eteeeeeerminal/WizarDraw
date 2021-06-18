package view;

class CommandData {
    public String name;
    public String command;
    public CommandData[] children;
    public CommandData(String name, String command) {
        new CommandData(name, command, null);
    }
    public CommandData(String name, String command, CommandData[] children) {
        this.name = name; this.command = command; this.children = children;
    }
}

public class CommandViewData {
    private static CommandData dataRoot = createNormalModeData();

    public static CommandData getDataRoot() {
        return dataRoot;
    }

    public static CommandData createNormalModeData() {
        return new CommandData(
                "Normal", "ESC|z",
                new CommandData[]{
                        createFileModeData(),
                        createColorModeData(),
                        createBrushModeData(),
                }
        );
    }
    public static CommandData createFileModeData() {
        return new CommandData(
                "File", "f",
                new CommandData[]{
                        new CommandData("Quit", "q"),
                }
        );
    }
    public static CommandData createColorModeData() {
        return new CommandData(
                "Color", "c",
                new CommandData[]{
                        new CommandData("ChangeColor", "c"),
                        new CommandData("Palette1", "1"),
                        new CommandData("Palette2", "2"),
                        new CommandData("Palette3", "3"),
                }
        );
    }
    public static CommandData createBrushModeData() {
        return new CommandData(
                "Brush", "s",
                new CommandData[]{
                        new CommandData("Rect", "r"),
                        new CommandData("FilledRect", "Shift+r"),
                }
        );
    }
}