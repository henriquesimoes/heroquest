package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons.NewGameButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.gametitle.GameTitle;

import java.awt.*;
import java.util.ArrayList;

public class StartMenu implements StateViewer {
    private final GameTitle GAME_TITLE;
    private final ArrayList<MenuButton> options;

    public StartMenu(Graphics2D graphics2D, GamePanel gamePanel) {
        this.options = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics2D, 200);

        options.add(new NewGameButton(graphics2D, gamePanel));
        options.add(new QuitButton(graphics2D, gamePanel));
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
