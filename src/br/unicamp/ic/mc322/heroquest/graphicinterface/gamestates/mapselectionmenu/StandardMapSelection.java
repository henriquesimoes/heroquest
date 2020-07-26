package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons.BackButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.selectionboxes.BoxedList;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import java.awt.*;
import java.util.ArrayList;

public class StandardMapSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private final BoxedList LIST_OF_MAPS;
    private final BackButton BACK_BUTTON;
    private MapManager mapManager;
    private GamePanel gamePanel;

    public StandardMapSelection(Graphics2D graphics, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapManager = new MapManager();
        this.LIST_OF_MAPS = new BoxedList(mapManager.getExistingMapNames(), graphics, gamePanel);
        this.GAME_TITLE = new GameTitle(graphics, 200);
        this.BACK_BUTTON = new BackButton(graphics, gamePanel);
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
