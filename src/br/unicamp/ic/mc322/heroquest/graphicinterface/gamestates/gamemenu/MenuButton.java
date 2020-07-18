package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamemenu;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;

import java.awt.*;

public abstract class MenuButton {
    private final int SELECTION_BOX_HEIGHT = 50;
    private final int SELECTION_BOX_WIDTH = 200;
    private int xPosition;
    private int yPosition;

    private String innerText;
    private Graphics2D graphics;


    public MenuButton(String innerText, Graphics2D graphics) {
        this.innerText = innerText;
        this.graphics = graphics;
    }

    /** Horizontal aligned in center */
    public void setPosition(int yPosition) {
        int x = (GameWindow.WINDOW_WIDTH - SELECTION_BOX_WIDTH) / 2;
    }

    private void renderSelectionBox() {
        graphics.setColor(new Color(61, 145, 166));
        graphics.setStroke(new BasicStroke(3));
        graphics.drawRect(xPosition, yPosition, SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);
    }
}
