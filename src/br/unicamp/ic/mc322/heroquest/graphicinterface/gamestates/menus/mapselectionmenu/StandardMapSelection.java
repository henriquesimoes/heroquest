package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes.BoxedList;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import java.awt.*;
import java.util.ArrayList;

public class StandardMapSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private final BoxedList LIST_OF_MAPS;
    private final Settings SETTINGS;
    private MapManager mapManager;
    private ScreenStateManager screenStateManager;

    public StandardMapSelection(Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager) {
        this.SETTINGS = settings;
        this.screenStateManager = screenStateManager;
        this.mapManager = new MapManager();
        this.LIST_OF_MAPS = new BoxedList(mapManager.getExistingMapNames(), graphics, SETTINGS, screenStateManager);
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
