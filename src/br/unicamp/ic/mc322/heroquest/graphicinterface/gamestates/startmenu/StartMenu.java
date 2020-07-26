package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.GUIMenuFactory;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class StartMenu implements Renderable {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;
    private GUIMenuFactory guiManager;

    public StartMenu(Graphics2D graphics2D, GraphicEngine graphicEngine) {
        this.options = new ArrayList<>();
        this.guiManager = new GUIMenuFactory(graphics2D, graphicEngine);

        this.GAME_TITLE = guiManager.getGameTitle(200);

        options.add(guiManager.getNewGameButton());
        options.add(guiManager.getQuitButton());
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return new ArrayList<>(options);
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

}
