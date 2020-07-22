package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenStates;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RandomMapButton extends MenuButton {
    private static final String BUTTON_TEXT = "Random map";;

    public RandomMapButton(Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(BUTTON_TEXT, graphics, screenStateManager);
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }

    @Override
    public void executeAction() {
        getScreenStateManager().setState(ScreenStates.LIST_OF_MAPS);
    }
}
