package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.MenuGUIManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class MapSelection implements Renderable {
    private MenuGUIManager guiManager;
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;

    public MapSelection(Graphics2D graphics, GraphicEngine graphicEngine) {
        this.options = new ArrayList<>();
        this.guiManager = new MenuGUIManager(graphics, graphicEngine);
        this.GAME_TITLE = new GameTitle(graphics, 200);

        options.add(guiManager.getRandomMapButton());
        options.add(guiManager.getGoToMapSelectionButton());
        options.add(guiManager.getQuitButton());

    }

    public void render() {
        GAME_TITLE.render();
        renderMenuOptions();
    }

    public void renderMenuOptions() {
        int y = 350;

        for (MenuButton menuButton : options) {
            menuButton.render(y);
            y += 100;
        }
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return new ArrayList<>(options);
    }
}
