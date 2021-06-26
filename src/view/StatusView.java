package view;

import command.ModeEnum;
import event.ModeEvent;
import event.ModeListener;

import javax.swing.*;

public class StatusView extends JPanel implements ModeListener {
    protected final JLabel modeLabel;

    public StatusView() {
        modeLabel = new JLabel(ModeEnum.NORMAL.name);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel("mode: "));
        add(modeLabel);
    }

    @Override
    public void modeChanged(ModeEvent e) {
        modeLabel.setText(e.getLatestMode().name);
    }
}