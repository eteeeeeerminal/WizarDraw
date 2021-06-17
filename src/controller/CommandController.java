package controller;

import model.CommandProcessor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandController implements KeyListener {
    protected final CommandProcessor cmdProcessor;
    public CommandController(CommandProcessor cp) {
        cmdProcessor = cp;
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        cmdProcessor.processCommand(e.getKeyCode(), e.getModifiersEx());
    }
    public void keyReleased(KeyEvent e) {}
}