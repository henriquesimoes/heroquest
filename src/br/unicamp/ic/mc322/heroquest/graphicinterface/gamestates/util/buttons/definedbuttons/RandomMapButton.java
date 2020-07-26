package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.State;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RandomMapButton extends MenuButton {
    private static final String BUTTON_TEXT = "Random map";
    private GamePanel gamePanel;

    public RandomMapButton(Graphics2D graphics, GamePanel gamePanel) {
        super(BUTTON_TEXT, graphics, gamePanel);
        this.gamePanel = gamePanel;
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }

    @Override
    public State executeAction() {
        String nickName = JOptionPane.showInputDialog("Choose nickname");

        if (nickName == null)
            nickName = "Player";

        gamePanel.setMap(new MapManager().generate());
        gamePanel.setName(nickName);
        return State.CHOOSE_CHARACTER;
    }
}
