package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.ListOfExistentMapsButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.RandomMapButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapSelection implements Renderable {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;
    private final ScreenStateManager screenStateManager;
    private String path;

    public MapSelection(Graphics2D graphics, ScreenStateManager screenStateManager) {
        this.screenStateManager = screenStateManager;
        this.options = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics, 200);
        options.add(new RandomMapButton(graphics, screenStateManager));
        options.add(new ListOfExistentMapsButton(graphics, screenStateManager));
        options.add(new QuitButton(graphics, screenStateManager));
        path = JOptionPane.showInputDialog("Enter a path");
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
