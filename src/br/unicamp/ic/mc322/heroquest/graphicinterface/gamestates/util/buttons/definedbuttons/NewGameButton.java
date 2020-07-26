package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.State;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class NewGameButton extends MenuButton {
    private static final String BUTTON_TEXT = "Play";

    public NewGameButton(Graphics2D graphics, GamePanel gamePanel) {
        super(BUTTON_TEXT, graphics, gamePanel);
    }

    @Override
    public State executeAction() {
        return State.MAP_SELECTION;
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }
}
