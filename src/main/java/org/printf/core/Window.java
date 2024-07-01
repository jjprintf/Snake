package org.printf.core;

import org.printf.game.Game;
import org.printf.game.Menu;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    Menu menuGame = new Menu(this);
    Game game = new Game(this);

    public Window() {
        this.add(menuGame);

        // Window Configuration
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 600));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);

        this.setVisible(true);

        this.add(game);
    }

    public void switchGame() {
        menuGame.setVisible(false);
        game.setVisible(true);
        game.requestFocus();
        game.init();
    }

    public void switchMenu() {
        game.stop();
        game.setVisible(false);
        menuGame.setVisible(true);
        menuGame.requestFocus();
    }
}