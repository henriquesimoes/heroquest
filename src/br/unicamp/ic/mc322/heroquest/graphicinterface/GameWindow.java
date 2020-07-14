package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.map.core.Map;

import javax.swing.*;

public class GameWindow extends JFrame {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 500;
    Map gameMap;

    public GameWindow(Map gameMap) {
        setTitle("HeroQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, WINDOW_WIDTH, WINDOW_HEIGHT);
        setContentPane(new GamePanel());

        pack();
        setResizable(false);
        setVisible(true);

        this.gameMap = gameMap;
    }
}
