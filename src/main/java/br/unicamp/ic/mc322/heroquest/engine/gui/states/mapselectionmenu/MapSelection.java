package br.unicamp.ic.mc322.heroquest.engine.gui.states.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.engine.gui.Clickable;
import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.StateViewer;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.GUIMenuFactory;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class MapSelection implements StateViewer {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;
    private GUIMenuFactory guiManager;

    public MapSelection(Graphics2D graphics, GamePanel gamePanel) {
        this.options = new ArrayList<>();
        this.guiManager = new GUIMenuFactory(graphics, gamePanel);
        this.GAME_TITLE = new GameTitle(graphics, 100);

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
