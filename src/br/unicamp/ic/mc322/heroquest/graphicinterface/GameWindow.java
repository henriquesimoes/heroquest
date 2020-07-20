package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenState;
import br.unicamp.ic.mc322.heroquest.map.MapManager;
import br.unicamp.ic.mc322.heroquest.map.core.Map;

import javax.swing.*;

public class GameWindow extends JFrame {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 900;
    private static ScreenState SCREEN_STATE;

    public GameWindow(Map gameMap) {
        setTitle("HeroQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 80, WINDOW_WIDTH, WINDOW_HEIGHT);
        setContentPane(new GamePanel(gameMap));

        pack();
        setResizable(false);
        setVisible(true);

        SCREEN_STATE = ScreenState.START_MENU;
    }

    public static ScreenState getScreenState() {
        return SCREEN_STATE;
    }

    public static void setScreenState(ScreenState state) {
        SCREEN_STATE = state;
    }
}
