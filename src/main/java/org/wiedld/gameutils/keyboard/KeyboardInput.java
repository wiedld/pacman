/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiedld.gameutils.keyboard;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author wiedld
 */
public class KeyboardInput extends JFrame {

    public static enum UserInput {

        UP, DOWN, LEFT, RIGHT,;
    }

    public static UserInput getKeyboardInput(KeyboardListener keys) {
        
        if (keys.keyDown(KeyEvent.VK_UP)) {
            return UserInput.UP;
        }
        if (keys.keyDown(KeyEvent.VK_DOWN)) {
            return UserInput.DOWN;
        }
        if (keys.keyDown(KeyEvent.VK_LEFT)) {
            return UserInput.LEFT;
        }
        if (keys.keyDown(KeyEvent.VK_RIGHT))
            return UserInput.RIGHT;
     
        return null;
    }

}
