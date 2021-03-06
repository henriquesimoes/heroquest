package br.unicamp.ic.mc322.heroquest.engine.gui.states.startmenu;

import br.unicamp.ic.mc322.heroquest.engine.gui.Clickable;
import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.StateViewer;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.GUIMenuFactory;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class StartMenu implements StateViewer {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;
    private GUIMenuFactory guiManager;

    public StartMenu(Graphics2D graphics2D, GamePanel gamePanel) {
        this.options = new ArrayList<>();
        this.guiManager = new GUIMenuFactory(graphics2D, gamePanel);
        this.GAME_TITLE = guiManager.getGameTitle(100);

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
