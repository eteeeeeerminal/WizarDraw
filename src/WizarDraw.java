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
        CommandController commandCtrl = new CommandController();
        commandCtrl.addCommandListener(model);
        this.addKeyListener(commandCtrl);

        CanvasView canvasView = new CanvasView(canvas);
        canvasView.addMouseListener(commandCtrl);
        canvasView.addMouseMotionListener(commandCtrl);
        this.add(canvasView, BorderLayout.CENTER);

        JPanel toolBar = new JPanel();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        toolBar.add(new PaletteView(palette));
        StatusView status = new StatusView();
        commandCtrl.addModeListener(status);
        toolBar.add(status);
        this.add(toolBar, BorderLayout.NORTH);

        CommandNavigator navi = new CommandNavigator();
        commandCtrl.addModeListener(navi);
        this.add(navi, BorderLayout.WEST);

        this.setTitle(appName);
        this.setSize(initialWidth, initialHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new WizarDraw();
    }
}