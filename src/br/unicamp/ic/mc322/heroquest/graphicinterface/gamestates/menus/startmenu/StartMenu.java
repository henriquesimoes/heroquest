package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.startmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.MenuButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.InstructionsButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.NewGameButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StartMenu extends JPanel implements Renderable {
    private final GameTitle GAME_TITLE;
    private ArrayList<MenuButton> menuOptions;

    public StartMenu(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.menuOptions = new ArrayList<>();
        this.GAME_TITLE = new GameTitle(graphics2D, 200);
        menuOptions.add(new NewGameButton(graphics2D));
        menuOptions.add(new InstructionsButton(graphics2D));
        menuOptions.add(new QuitButton(graphics2D));
    }

    public ArrayList<Coordinate> getClickableZones() {
        return null;
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

}
