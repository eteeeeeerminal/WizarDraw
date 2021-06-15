package controller;

import model.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandController implements KeyListener {
    protected CommandProcessor cProcessor;
    public CommandController(DrawModel m) {
        cProcessor = new CommandProcessor(m);
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        cProcessor.processCommand(e.getKeyCode(), e.getModifiers());
    }
    public void keyReleased(KeyEvent e) {}
}

