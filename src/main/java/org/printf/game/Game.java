package org.printf.game;

import org.printf.core.Engine;
import org.printf.core.Window;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends Engine {
    private static final int MAX_APPLES = 5;

    enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    // position snake
    protected int[] positionX = new int[CELLS];
    protected int[] positionY = new int[CELLS];

    private int snakeLength = 3;
    private int score;
    private final int[] applesX = new int[5];
    private final int[] applesY = new int[5];
    private Direction directionSnake;

    Window _window;
    public Game(Window windowFrame) {
        super(windowFrame);
        _window = windowFrame;

        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(new GameKeyAdapter());
    }

    @Override
    public void start() {
        super.start();

        snakeLength = 3;
        score = 0;
        directionSnake = Direction.RIGHT;

        spawnApples();

        for (int i = 0; i < snakeLength; i++) {
            positionX[i] = 0;
            positionY[i] = 0;
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // move logic
        for (int i = snakeLength; i > 0; i--) {
            positionX[i] = positionX[i-1];
            positionY[i] = positionY[i-1];
        }

        switch (directionSnake) {
            case Direction.UP:
                positionY[0] = positionY[0] - CELL_SIZE;
                break;
            case Direction.DOWN:
                positionY[0] = positionY[0] + CELL_SIZE;
                break;
            case Direction.LEFT:
                positionX[0] = positionX[0] - CELL_SIZE;
                break;
            case Direction.RIGHT:
                positionX[0] = positionX[0] + CELL_SIZE;
                break;
        }

        for (int i = 0; i < MAX_APPLES; i++) {
            if ((positionX[0] == applesX[i]) && (positionY[0] == applesY[i])) {
                snakeLength++;
                score++;
                applesX[i] = (int) (Math.random() * (WIDTH / CELL_SIZE)) * CELL_SIZE;
                applesY[i] = (int) (Math.random() * (HEIGHT / CELL_SIZE)) * CELL_SIZE;
            }
        }

        collisions();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getRunning()) {
            gameOver(g);
            return;
        }

        for (int i = 0; i < MAX_APPLES; i++) {
            g.setColor(Color.RED);
            g.fillOval(applesX[i], applesY[i], CELL_SIZE, CELL_SIZE);
        }

        for (int i = 0; i < snakeLength; i++) {
            if (i == 0) {
                g.setColor(Color.GREEN);
                g.fillRect(positionX[i], positionY[i], CELL_SIZE, CELL_SIZE);
            } else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(positionX[i], positionY[i], CELL_SIZE, CELL_SIZE);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (WIDTH - metrics.stringWidth("Score: " + score)) / 2, g.getFont().getSize());
    }

    private void collisions() {
        // Check if head collides with body
        for (int i = snakeLength; i > 0; i--) {
            if ((positionX[0] == positionX[i]) && (positionY[0] == positionY[i]))
                setRunning(false);
        }

        // Check if head touches left border
        if (positionX[0] < 0) setRunning(false);

        // Check if head touches right border
        if (positionX[0] >= WIDTH) setRunning(false);

        // Check if head touches top border
        if (positionY[0] < 0) setRunning(false);

        // Check if head touches bottom border
        if (positionY[0] >= HEIGHT) setRunning(false);

        if (getRunning()) stop();
    }

    private void spawnApples() {
        for (int i = 0; i < MAX_APPLES; i++) {
            applesX[i] = (int) (Math.random() * (500 / CELL_SIZE)) * CELL_SIZE;
            applesY[i] = (int) (Math.random() * (400 / CELL_SIZE)) * CELL_SIZE;
        }
    }

    private void gameOver(Graphics g) {
        stop();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

        // Tutorial back to menu
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        metrics = getFontMetrics(g.getFont());
        g.drawString("Press 'esc' to return to the menu", (WIDTH - metrics.stringWidth("Press 'esc' to return to the menu")) / 2, (HEIGHT / 2) + 200);

        // Score text
        g.setFont(new Font("Arial", Font.BOLD, 40));
        metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (WIDTH - metrics.stringWidth("Score: " + score)) / 2, g.getFont().getSize());
    }

    private class GameKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    if (getRunning()) _window.switchMenu();
                case KeyEvent.VK_LEFT:
                    if (directionSnake != Direction.RIGHT) directionSnake = Direction.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (directionSnake != Direction.LEFT) directionSnake = Direction.RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    if (directionSnake != Direction.DOWN) directionSnake = Direction.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    if (directionSnake != Direction.UP) directionSnake = Direction.DOWN;
                    break;
            }
        }
    }
}