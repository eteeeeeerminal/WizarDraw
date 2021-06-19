package view;

import event.PaletteEvent;
import event.PaletteListener;
import fig.*;
import model.PaletteAndBrush;

import javax.swing.*;
import java.awt.*;

public class PaletteView extends JPanel implements PaletteListener {
    protected int size, margin;
    protected Figure[] paletteFig;
    protected final PaletteAndBrush palette;
    public PaletteView(PaletteAndBrush p) {
        palette = p;
        palette.addListener(this);

        size = 30; margin = 5;
        int outerSize = size+2*margin;
        setPreferredSize(new Dimension(outerSize*4, outerSize));
        setMinimumSize(new Dimension(outerSize*3, outerSize));
        setMaximumSize(new Dimension(outerSize*5, outerSize));

        Color[] paletteColors = palette.getPalette();
        paletteFig = new Figure[3];
        for (int i=0; i<3; i++) {
            paletteFig[i] = new RectangleFigure(
                    (int)(outerSize*(i*0.7+1)), margin,
                    (int)(size*0.7), (int)(size*0.7),
                    paletteColors[i], true
            );
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Figure brush = palette.createFigure(margin, margin);
        brush.setSize(size, size);
        brush.setColor(palette.getBrushColor());
        brush.draw(g);

        Color[] paletteColors = palette.getPalette();
        for (int i=0; i<3; i++) {
            paletteFig[i].setColor(paletteColors[i]);
            paletteFig[i].draw(g);
        }
    }
    public void paletteUpdated(PaletteEvent e) {
        repaint();
    }
}