package view;

import event.CommandEvent;
import event.CommandListener;
import model.CommandProcessor;

import javax.swing.*;
import java.util.HashMap;

public class StatusView extends JPanel implements CommandListener {
    protected final JLabel modeLabel;
    protected final CommandProcessor cmdProcessor;
    protected final HashMap<CommandProcessor.ModeEnum, String> modeNames;
    public StatusView(CommandProcessor cp) {
        cmdProcessor = cp;
        cmdProcessor.addListener(this);
        modeNames = createModeNames();

        modeLabel = new JLabel(modeNames.get(CommandProcessor.ModeEnum.NORMAL));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel("mode: "));
        add(modeLabel);
    }
    public void modeChanged(CommandEvent e) {
        modeLabel.setText(modeNames.get(cmdProcessor.getMode()));
    }

    protected static HashMap<CommandProcessor.ModeEnum, String> createModeNames() {
        HashMap<CommandProcessor.ModeEnum, String> modeNames = new HashMap<>();
        modeNames.put(
                CommandProcessor.ModeEnum.NORMAL,
                CommandViewData.createNormalModeData().name
        );
        modeNames.put(
                CommandProcessor.ModeEnum.FILE,
                CommandViewData.createFileModeData().name
        );
        modeNames.put(
                CommandProcessor.ModeEnum.COLOR,
                CommandViewData.createColorModeData().name
        );
        modeNames.put(
                CommandProcessor.ModeEnum.BRUSH,
                CommandViewData.createBrushModeData().name
        );
        return modeNames;
    }
}