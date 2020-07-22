package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.ListOfExistentMapsButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.RandomMapButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private ArrayList<MenuButton> menuOptions;
    private ScreenStateManager screenStateManager;

    public MapSelection(Graphics2D graphics, ScreenStateManager screenStateManager) {
        this.screenStateManager = screenStateManager;
        this.menuOptions = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics, 200);
        menuOptions.add(new RandomMapButton(graphics, screenStateManager));
        menuOptions.add(new ListOfExistentMapsButton(graphics, screenStateManager));
        menuOptions.add(new QuitButton(graphics, screenStateManager));
    }

    public void render() {
        GAME_TITLE.render();
        renderMenuOptions();
    }

    public void renderMenuOptions() {
        int y = 350;

        for (MenuButton menuButton : menuOptions) {
            menuButton.render(y);
            y += 100;
        }
    }

    @Override
    public ArrayList<Clickable> getClickableZones() {
        return null;
    }
}
