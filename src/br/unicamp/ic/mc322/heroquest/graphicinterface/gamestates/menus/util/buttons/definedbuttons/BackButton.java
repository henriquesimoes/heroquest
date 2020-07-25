package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BackButton extends MenuButton {
    ScreenStateManager screenStateManager;

    public BackButton(Graphics2D graphics, ScreenStateManager screenStateManager) {
        super("BACK", graphics, screenStateManager);

        this.screenStateManager = screenStateManager;
    }

    @Override
    public Rectangle2D getBounds() {
        return getBoxBounds();
    }

    @Override
    public void executeAction() {
        screenStateManager.setState(screenStateManager.getPrevState());
    }
}
