package view;

import command.ModeEnum;
import controller.CommandController;
import event.ModeEvent;
import event.ModeListener;

import javax.swing.*;

public class StatusView extends JPanel implements ModeListener {
    protected final JLabel modeLabel;
    protected final CommandController cmdProcessor;

    public StatusView(CommandController cp) {
        cmdProcessor = cp;
        cmdProcessor.addModeListener(this);

        modeLabel = new JLabel(ModeEnum.NORMAL.name);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel("mode: "));
        add(modeLabel);
    }

    public void modeChanged(ModeEvent e) {
        modeLabel.setText(cmdProcessor.getMode().name);
    }
}