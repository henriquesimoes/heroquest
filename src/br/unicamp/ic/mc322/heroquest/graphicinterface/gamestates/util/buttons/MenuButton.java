package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;

public abstract class MenuButton extends GenericButton implements Clickable {
    private final int SELECTION_BOX_HEIGHT = 50;
    private final int SELECTION_BOX_WIDTH = 200;
    private final GamePanel gamePanel;

    public MenuButton(String innerText, Graphics2D graphics, GamePanel gamePanel) {
        super(innerText, graphics);
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
