package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class QuitButton extends MenuButton {
    private static final String BUTTON_TEXT = "Quit";

    public QuitButton(Graphics2D graphics, GamePanel gamePanel) {
        super(BUTTON_TEXT, graphics, gamePanel);
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }

    @Override
    public States executeAction() {
        return States.QUIT;
    }
}
