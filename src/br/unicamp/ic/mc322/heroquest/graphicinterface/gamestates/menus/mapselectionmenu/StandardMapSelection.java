package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes.BoxedList;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StandardMapSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private final BoxedList LIST_OF_MAPS;
    private ScreenStateManager screenStateManager;

    public StandardMapSelection(Graphics2D graphics, ScreenStateManager screenStateManager) {
        this.screenStateManager = screenStateManager;
        this.LIST_OF_MAPS = new BoxedList(GamePanel.manager.getExistingMapNames(), graphics);
        this.GAME_TITLE = new GameTitle(graphics, 200);
    }

    public void render() {
        GAME_TITLE.render();
        LIST_OF_MAPS.render(350);
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return null;
    }


}
