import javax.swing.JFrame;
import java.awt.BorderLayout;

import model.*;
import controller.*;
import view.*;

// Main class
class WizarDraw extends JFrame {
    public static final String appName = "WizarDraw";

    public WizarDraw() {
        PaletteAndBrush palette = new PaletteAndBrush();
        Canvas canvas = new Canvas();
        DrawModel model = new DrawModel(palette, canvas);
        CommandProcessor cp = new CommandProcessor(model);
        DrawController drawCtrl = new DrawController(model);
        CommandController commandCtrl = new CommandController(cp);
        this.addKeyListener(commandCtrl);

        this.add(new CanvasView(canvas, drawCtrl), BorderLayout.CENTER);
        this.add(new PaletteView(palette), BorderLayout.NORTH);

        this.setTitle(appName);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new WizarDraw();
    }
}