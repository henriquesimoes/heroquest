package br.unicamp.ic.mc322.heroquest.graphicinterface;

import javax.swing.*;

public class GameWindow extends JFrame {
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 500;


    public GameWindow() {
        setTitle("HeroQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, WINDOW_WIDTH, WINDOW_HEIGHT);
        setContentPane(new GamePanel());

        pack();
        setResizable(false);
        setVisible(true);
    }
}
