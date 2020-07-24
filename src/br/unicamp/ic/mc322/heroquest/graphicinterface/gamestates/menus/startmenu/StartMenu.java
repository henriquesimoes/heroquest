package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.startmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.NewGameButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class StartMenu implements Renderable {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;

    public StartMenu(Graphics2D graphics2D, ScreenStateManager screenStateManager) {
        this.options = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics2D, 200);

        options.add(new NewGameButton(graphics2D, screenStateManager));
        options.add(new QuitButton(graphics2D, screenStateManager));
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
