package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.ListOfExistentMapsButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.RandomMapButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class MapSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;
    private final Settings SETTINGS;

    public MapSelection(Graphics2D graphics, ScreenStateManager screenStateManager) {
        this.SETTINGS = new Settings();
        this.options = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics, 200);
        options.add(new RandomMapButton(graphics, SETTINGS, screenStateManager));
        options.add(new ListOfExistentMapsButton(graphics, screenStateManager));
        options.add(new QuitButton(graphics, screenStateManager));

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
