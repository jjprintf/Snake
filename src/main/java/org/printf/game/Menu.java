package org.printf.game;

import org.printf.core.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
    static Window _window;

    public Menu(Window frame) {
        _window = frame;
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setFocusable(true);
        this.requestFocus();

        render();
    }

    private void render() {
        this.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Snake Game");
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        this.add(title);

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton startButton = createButton("Start Game", new StartButtonListener());
        this.add(startButton);

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton exitButton = createButton("Exit", new ExitButtonListener());
        this.add(exitButton);

        this.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel credits = new JLabel("by jjprintf");
        credits.setForeground(Color.WHITE);
        credits.setAlignmentX(CENTER_ALIGNMENT);
        credits.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(credits);

        this.add(Box.createVerticalGlue());
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.addActionListener(listener);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setText("<html><u>" + text + "</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setText(text);
            }
        });

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private static class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logica para iniciar el juego
            _window.switchGame();
        }
    }

    private static class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}