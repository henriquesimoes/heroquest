package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BackButton extends MenuButton {
    GamePanel gamePanel;

    public BackButton(Graphics2D graphics, GamePanel gamePanel) {
        super("BACK", graphics, gamePanel);

        this.gamePanel = gamePanel;
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }

    @Override
    public States executeAction() {
        return States.GO_TO_PREV;
    }
}
