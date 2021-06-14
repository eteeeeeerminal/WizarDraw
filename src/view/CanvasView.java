package view;

import fig.*;
import model.*;
import controller.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasView extends JPanel implements DrawModelListener {
    protected DrawModel model;
    public CanvasView(DrawModel m, DrawController dc) {
        this.setBackground(Color.white);
        this.addMouseListener(dc);
        this.addMouseMotionListener(dc);
        model = m;
        model.addListener(this);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<Figure> figs = model.getFigures();
        for(Figure f: figs) {
            f.draw(g);
        }
    }
    public void modelUpdated(DrawModelEvent e) {
        repaint();
    }
}