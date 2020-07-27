package br.unicamp.ic.mc322.heroquest.engine.gui.states.util.buttons;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;

public class MenuButton extends GenericButton {
    private final int SELECTION_BOX_HEIGHT = 50;
    private final int SELECTION_BOX_WIDTH = 200;
    private final GamePanel gamePanel;

    public MenuButton(String innerText, Command command, Graphics2D graphics, GamePanel gamePanel) {
        super(innerText, command, graphics);
        this.gamePanel = gamePanel;
        setFontSize(20);
        setBoxColor(new Color(61, 145, 166));
        setBoxDimensions(new Dimension(SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT));
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void render(int y) {
        setPosition(y);
        super.render();
    }
}
