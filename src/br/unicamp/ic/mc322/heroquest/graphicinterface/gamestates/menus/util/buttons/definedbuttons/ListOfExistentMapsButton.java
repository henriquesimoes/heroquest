package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStates;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ListOfExistentMapsButton extends MenuButton {
    private static final String BUTTON_TEXT = "Select map";

    public ListOfExistentMapsButton(Graphics2D graphics, ScreenStateManager screenStateManager) {
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
