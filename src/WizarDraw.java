import javax.swing.*;
import java.awt.BorderLayout;

import model.*;
import controller.*;
import view.*;

/**
 * Main Class of WizarDraw
 * <p>
 *     This class combines Model, View, and Controller, then constructs a main window.
 *     This class isn't called from other class.
 */
class WizarDraw extends JFrame {
    /**
     * Set the title for the main window frame
     */
    public static final String appName = "WizarDraw";
    /**
     * Main window width initialized by this value
     */
    public static final int initialWidth = 1280;
    /**
     * Main window height initialized by this value
     */
    public static final int initialHeight = 720;

    /**
     * Initializes and Constructs Model, View, and Controller
     */
    public WizarDraw() {
        // initialize Model and Controller
        PaletteAndBrush palette = new PaletteAndBrush();
        Canvas canvas = new Canvas();
        DrawModel model = new DrawModel(palette, canvas);
        CommandController commandCtrl = new CommandController();
        commandCtrl.addCommandListener(model);
        addKeyListener(commandCtrl);

        // initialize Canvas View
        CanvasView canvasView = new CanvasView(canvas);
        canvasView.addMouseListener(commandCtrl);
        canvasView.addMouseMotionListener(commandCtrl);
        add(canvasView, BorderLayout.CENTER);

        // initialize Tool Bar
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        toolBar.add(new PaletteView(palette));
        StatusView status = new StatusView();
        commandCtrl.addModeListener(status);
        toolBar.add(status);
        add(toolBar, BorderLayout.NORTH);

        // initialize Navigator View
        CommandNavigator navi = new CommandNavigator();
        commandCtrl.addModeListener(navi);
        add(navi, BorderLayout.WEST);

        // initialize window
        setTitle(appName);
        setSize(initialWidth, initialHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Constructs WizarDraw
     * @param args isn't used
     */
    public static void main(String[] args) {
        new WizarDraw();
    }
}