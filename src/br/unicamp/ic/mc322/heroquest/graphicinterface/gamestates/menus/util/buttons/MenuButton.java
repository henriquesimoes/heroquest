package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class MenuButton extends GenericButton implements Clickable {
    private final int SELECTION_BOX_HEIGHT = 50;
    private final int SELECTION_BOX_WIDTH = 200;
    private final ScreenStateManager screenStateManager;

    public MenuButton(String innerText, Graphics2D graphics, ScreenStateManager screenStateManager) {
        super(innerText, graphics);
        this.screenStateManager = screenStateManager;
        setFontSize(20);
        setBoxColor(new Color(61, 145, 166));
        setBoxDimensions(new Dimension(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT));
    }

    public ScreenStateManager getScreenStateManager() {
        return screenStateManager;
    }

    public void render(int y) {
        setPosition(y);
        super.render();
    }
}
