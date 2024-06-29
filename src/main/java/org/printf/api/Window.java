package org.printf.api;

import org.printf.game.Game;
import org.printf.game.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window extends JFrame {
    Menu menu = new Menu(this);
    Game game = new Game(this);

    public Window() {
        this.add(menu);

        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.add(game);
    }

    public void switchGame() {
        menu.setVisible(false);
        game.setVisible(true);
        game.requestFocus();
        game.start();
    }

    public void switchMenu() {
        game.stop();
        game.setVisible(false);
        menu.setVisible(true);
        menu.requestFocus();
    }
}
