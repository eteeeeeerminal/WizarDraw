import javax.swing.*;
import java.awt.*;

import model.*;
import controller.*;
import view.*;

//////////////////////////////////////////////////
// Main class
//   (GUIを組み立てているので，view の一部と考えてもよい)
class WizarDraw extends JFrame {
    DrawModel model;
    CanvasView view;
    DrawController drawCont;
    CommandController commandCont;
    public WizarDraw() {
        model = new DrawModel();
        drawCont = new DrawController(model);
        commandCont = new CommandController(model);
        this.addKeyListener(commandCont);
        view = new CanvasView(model, drawCont);
        this.setBackground(Color.black);
        this.setTitle("Draw Editor");
        this.setSize(500, 500);
        this.add(view);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new WizarDraw();
    }
}