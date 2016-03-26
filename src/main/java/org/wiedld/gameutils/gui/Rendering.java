/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wiedld.gameutils.gui;

/**
 *
 * @author wiedld
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import org.wiedld.gameutils.keyboard.*;
import org.wiedld.gameutils.keyboard.KeyboardInput.UserInput;

public class Rendering extends JFrame implements Runnable {

    private final FrameRate frameRate;
    private BufferStrategy bs;
    private volatile boolean running;
    private Thread gameThread;

    private static final int SCREEN_W = 1000;
    private static final int SCREEN_H = 1000;

    private final KeyboardListener keyboard;

    public Rendering() {
        this.frameRate = new FrameRate();
        this.keyboard = new KeyboardListener();
    }

    protected void createAndShowGUI() {

        Canvas canvas = new Canvas();
        canvas.setSize(SCREEN_W, SCREEN_H);
        canvas.setBackground(Color.BLACK);
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Active Rendering");
        setIgnoreRepaint(true);
        pack();

        canvas.addKeyListener(this.keyboard);

        setVisible(true);
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        running = true;
        frameRate.initialize();
        while (running) {
            gameLoop();
        }
    }

    private void gameLoop() {
        processInput();
        renderFrame();
        sleep(10L);
    }

    private void processInput() {
        UserInput action = KeyboardInput.getKeyboardInput(keyboard);
        System.out.println(action);
    }

    public void renderFrame() {
        do {
            do {
                Graphics g = null;
                try {
                    g = bs.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
                    render(g);
                } finally {
                    if (g != null) {
                        g.dispose();
                    }
                }
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }

    private void render(Graphics g) {
        frameRate.calculate();
        g.setColor(Color.GREEN);

//        double x = 5;
//        double y = 5;
//double angle; 
//angle = 20 * (Math.sin((x + y)*2*Math.PI/50) + 1);
//g.fillArc(x, y, 50, 50, angle/2, 360-angle);

        g.drawString(frameRate.getFrameRate(), 30, 30);
    }

    private void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ex) {
        }
    }

    protected void onWindowClosing() {
        try {
            running = false;
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        final Rendering app = new Rendering();
        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                app.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                app.createAndShowGUI();
            }
        });
    }

}
