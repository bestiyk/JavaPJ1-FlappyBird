/*
 * Copyright (c) 2019.
 * Author Matyas Dedek
 * Project JavaPJ1-FlappyBird
 *
 */

package logic;

import javax.swing.*;

import assets.Keyboard;

public class Run {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
