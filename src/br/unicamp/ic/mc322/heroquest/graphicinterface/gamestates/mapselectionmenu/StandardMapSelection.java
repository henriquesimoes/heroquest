package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.GUIMenuFactory;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.selectionboxes.BoxedMapList;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import java.awt.*;
import java.util.ArrayList;

public class StandardMapSelection implements StateViewer {
    private final GameTitle GAME_TITLE;
    private final BoxedMapList LIST_OF_MAPS;
    private final MenuButton BACK_BUTTON;
    private GUIMenuFactory guiManager;
    private MapManager mapManager;

    public StandardMapSelection(Graphics2D graphics, GamePanel gamePanel) {
        this.mapManager = new MapManager();
        this.guiManager = new GUIMenuFactory(graphics, gamePanel);
        this.LIST_OF_MAPS = guiManager.getBoxedMapList(this.mapManager);
        this.GAME_TITLE = guiManager.getGameTitle(100);
        this.BACK_BUTTON = guiManager.getBackButton();
    }

    @Override
    public void render() {
        GAME_TITLE.render();
        LIST_OF_MAPS.render(350);
        BACK_BUTTON.render(700);
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        ArrayList<Clickable> clickables = new ArrayList<>(LIST_OF_MAPS.getOptions());
        clickables.add(BACK_BUTTON);

        return clickables;
    }

}
