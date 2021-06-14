import javax.swing.*;
import java.awt.*;

import model.*;
import controller.*;
import view.*;

// Main class
class WizarDraw extends JFrame {
    public static final String appName = "WizarDraw";

    public WizarDraw() {
        DrawModel model = new DrawModel();
        DrawController drawCtrl = new DrawController(model);
        CommandController commandCont = new CommandController(model);
        this.addKeyListener(commandCont);

        this.add(new CanvasView(model, drawCtrl), BorderLayout.CENTER);

        this.setBackground(Color.black);
        this.setTitle(appName);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new WizarDraw();
    }
}