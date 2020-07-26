package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.mapselectionmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons.ListOfExistentMapsButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons.RandomMapButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class MapSelection implements StateViewer {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;

    public MapSelection(Graphics2D graphics, GamePanel gamePanel) {
        this.options = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics, 200);
        options.add(new RandomMapButton(graphics, gamePanel));
        options.add(new ListOfExistentMapsButton(graphics, gamePanel));
        options.add(new QuitButton(graphics, gamePanel));

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
