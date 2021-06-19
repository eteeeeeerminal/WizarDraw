import javax.swing.*;
import java.awt.BorderLayout;

import model.*;
import controller.*;
import view.*;

// Main class
class WizarDraw extends JFrame {
    public static final String appName = "WizarDraw";
    public static final int initialWidth = 1280;
    public static final int initialHeight = 720;

    public WizarDraw() {
        PaletteAndBrush palette = new PaletteAndBrush();
        Canvas canvas = new Canvas();
        DrawModel model = new DrawModel(palette, canvas);
        DrawController drawCtrl = new DrawController(model);
        CommandController commandCtrl = new CommandController();
        commandCtrl.addCommandListener(model);
        this.addKeyListener(commandCtrl);

        this.add(new CanvasView(canvas, drawCtrl), BorderLayout.CENTER);

        JPanel toolBar = new JPanel();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        toolBar.add(new PaletteView(palette));
        StatusView status = new StatusView();
        commandCtrl.addModeListener(status);
        toolBar.add(status);
        this.add(toolBar, BorderLayout.NORTH);

        this.setTitle(appName);
        this.setSize(initialWidth, initialHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new WizarDraw();
    }
}