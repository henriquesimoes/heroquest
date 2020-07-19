package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.Renderable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.buttons.InstructionsButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.buttons.NewGameButton;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.buttons.QuitButton;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.util.ArrayList;

public class GameMenu extends Component implements Renderable {
    private final GameTitle GAME_TITLE;
    private final NewGameButton NEW_GAME_BUTTON;
    private final InstructionsButton INSTRUCTIONS_BUTTON;
    private final QuitButton QUIT_BUTTON;

    public GameMenu(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.GAME_TITLE = new GameTitle(graphics2D, 200);
        this.NEW_GAME_BUTTON = new NewGameButton(graphics2D, 350);
        this.INSTRUCTIONS_BUTTON = new InstructionsButton(graphics2D, 450);
        this.QUIT_BUTTON = new QuitButton(graphics2D, 550);

    }

    public ArrayList<Coordinate> getClickableZones() {
        return null;
    }

    public void render() {
        GAME_TITLE.render();
        NEW_GAME_BUTTON.render();
        INSTRUCTIONS_BUTTON.render();
        QUIT_BUTTON.render();
    }
}
