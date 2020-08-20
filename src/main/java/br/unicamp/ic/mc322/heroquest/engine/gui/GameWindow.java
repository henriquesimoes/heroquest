package br.unicamp.ic.mc322.heroquest.engine.gui;

import javax.swing.*;

public class GameWindow extends JFrame {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 900;

    public GameWindow() {
        setTitle("HeroQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 80, WINDOW_WIDTH, WINDOW_HEIGHT);
        setContentPane(new GamePanel());

        setResizable(false);
        setVisible(true);
        pack();
    }
}
