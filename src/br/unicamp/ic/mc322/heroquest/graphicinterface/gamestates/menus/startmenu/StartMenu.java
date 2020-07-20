package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.startmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.InstructionsButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.NewGameButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.buttons.definedbuttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.gametitle.GameTitle;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.util.ArrayList;

public class StartMenu extends Component implements Renderable {
    private final GameTitle GAME_TITLE;
    private final NewGameButton NEW_GAME_BUTTON;
    private final InstructionsButton INSTRUCTIONS_BUTTON;
    private final QuitButton QUIT_BUTTON;

    public StartMenu(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.GAME_TITLE = new GameTitle(graphics2D, 200);
        this.NEW_GAME_BUTTON = new NewGameButton(graphics2D);
        this.INSTRUCTIONS_BUTTON = new InstructionsButton(graphics2D);
        this.QUIT_BUTTON = new QuitButton(graphics2D);

    }

    public ArrayList<Coordinate> getClickableZones() {
        return null;
    }

    public void render() {
        GAME_TITLE.render();
        NEW_GAME_BUTTON.render(350);
        INSTRUCTIONS_BUTTON.render(450);
        QUIT_BUTTON.render(550);
    }
}
