package org.printf.game;

import org.printf.api.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
    private Game game;
    Window _window;

    public Menu(JFrame frame) {
        _window = (Window) frame;
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setFocusable(true);
        this.requestFocus();

        // Agregar espacio flexible en la parte superior para centrar
        this.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Snake Game");
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        this.add(title);

        // Agregar espacio entre el titulo y los botones
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton startButton = createButton("Start Game", new StartGameListener());
        this.add(startButton);

        // Agregar espacio entre los botones
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton exitButton = createButton("Exit", new ExitGameListener());
        this.add(exitButton);

        // Agregar espacio flexible en la parte inferior
        this.add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar graficos adicionales
        g.setColor(Color.WHITE);
        g.drawString("Welcome to Snake Game!", 10, 20);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("by jjprintf", (800 - metrics.stringWidth("by jjprintf")) / 2, (600 + 350) / 2);
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

    private class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logica para iniciar el juego
            _window.switchGame();
        }
    }

    private class ExitGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
