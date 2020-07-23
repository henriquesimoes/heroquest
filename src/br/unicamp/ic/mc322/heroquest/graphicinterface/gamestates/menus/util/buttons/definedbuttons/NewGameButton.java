package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStates;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class NewGameButton extends MenuButton {
    private static final String BUTTON_TEXT = "Play";

    public NewGameButton(Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(BUTTON_TEXT, graphics, screenStateManager);
    }

    @Override
    public void executeAction() {
        getScreenStateManager().setState(ScreenStates.MAP_SELECTION);
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }
}
