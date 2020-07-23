package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes.BoxedList;

import java.awt.*;
import java.util.ArrayList;

public class StandardMapSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private final BoxedList LIST_OF_MAPS;
    private final ScreenStateManager screenStateManager;

    public StandardMapSelection(Graphics2D graphics, ScreenStateManager screenStateManager) {
        this.screenStateManager = screenStateManager;
        this.LIST_OF_MAPS = new BoxedList(GamePanel.manager.getExistingMapNames(), graphics, screenStateManager);
        this.GAME_TITLE = new GameTitle(graphics, 200);
    }

    @Override
    public void render() {
        GAME_TITLE.render();
        LIST_OF_MAPS.render(350);
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return LIST_OF_MAPS.getOptions();
    }

}
