package br.unicamp.ic.mc322.heroquest.engine.gui.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private char key;
    private volatile boolean waiting;

    public KeyboardInput() {
        waiting = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (waiting)
            key = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        waiting = false;
    }

    public String getKey() {
        waiting = true;
        while (waiting) ;
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return Character.toString(key).toUpperCase();
    }
}
