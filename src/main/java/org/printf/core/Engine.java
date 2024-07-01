package org.printf.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.currentTimeMillis;

public class Engine extends JPanel {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;
    protected static final int CELL_SIZE = 20;
    protected static final int CELLS = (800 * 600) / CELL_SIZE;
    private static final int DELAY = 100;

    private Timer updateTimer;
    private long lastTime;
    private boolean running;

    Window _window;

    public Engine(Window windowFrame) {
        _window = windowFrame;

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.setFocusable(true);
        this.requestFocus();
    }

    public void init() {
        running = true;
        lastTime = currentTimeMillis();

        start();

        updateTimer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!running) return;
                long currentTime = currentTimeMillis();
                float deltaTime = (currentTime - lastTime) / 1000.0f;
                lastTime = currentTime;

                update(deltaTime);
            }
        });
        updateTimer.start();
    }

    public void start() {}

    public void update(float deltaTime) {
        repaint();
    }

    public void stop() {
        running = false;
        if (updateTimer != null) updateTimer.stop();
    }

    protected void setRunning(boolean value) {
        running = value;
    }

    protected boolean getRunning() {
        return !running;
    }
}