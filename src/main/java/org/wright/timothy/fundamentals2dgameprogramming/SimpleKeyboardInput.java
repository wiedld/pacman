/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wright.timothy.fundamentals2dgameprogramming;

import java.awt.event.*;

/**
 *
 * @author TImothy Wright
 */

public class SimpleKeyboardInput implements KeyListener {

    private boolean[] keys;

    public SimpleKeyboardInput() {
        keys = new boolean[256];
    }

    public synchronized boolean keyDown(int keyCode) {
        return keys[keyCode];
    }

    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true;
        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        // Not needed
    }
}
