package view;

import event.CanvasEvent;
import event.CanvasListener;
import fig.Figure;
import model.Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Iterator;

public class CanvasView extends JPanel implements CanvasListener {
    protected final Canvas canvas;
    public CanvasView(Canvas m) {
        setBackground(Color.white);
        canvas = m;
        canvas.addListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayDeque<Figure> figs = canvas.getFigures();
        Iterator<Figure> iterator = figs.descendingIterator();
        while(iterator.hasNext()) {
            iterator.next().draw(g);
        }
    }

    @Override
    public void canvasUpdated(CanvasEvent e) {
        repaint();
    }
}