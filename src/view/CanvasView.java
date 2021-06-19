package view;

import event.CanvasEvent;
import event.CanvasListener;
import fig.Figure;
import controller.DrawController;
import model.Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasView extends JPanel implements CanvasListener {
    protected final Canvas canvas;
    public CanvasView(Canvas m, DrawController dc) {
        this.setBackground(Color.white);
        this.addMouseListener(dc);
        this.addMouseMotionListener(dc);
        canvas = m;
        canvas.addListener(this);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<Figure> figs = canvas.getFigures();
        for(Figure f: figs) {
            f.draw(g);
        }
    }
    public void canvasUpdated(CanvasEvent e) {
        repaint();
    }
}