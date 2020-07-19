package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.startmenu.buttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class MenuButton {
    private final int SELECTION_BOX_HEIGHT = 50;
    private final int SELECTION_BOX_WIDTH = 200;
    private final int TOP_PADDING = 30;
    private Coordinate boxPosition;
    private Coordinate innerTextPosition;

    private final Font buttonFont;
    private final String innerText;
    private final Graphics2D graphics;


    public MenuButton(String innerText, Graphics2D graphics) {
        this.innerText = innerText;
        this.graphics = graphics;
        this.buttonFont = new Font("Helvetica", Font.BOLD, 20);
    }

    /** Horizontal aligned in center */
    public void setPosition(int y) {
        int selectionBoxXCoord = (GameWindow.WINDOW_WIDTH - SELECTION_BOX_WIDTH) / 2;
        Rectangle2D innerTextBounds = buttonFont.getStringBounds(innerText, graphics.getFontRenderContext());

        int a = (int) innerTextBounds.getWidth();

        int innerTextXCoord = selectionBoxXCoord + ((SELECTION_BOX_WIDTH - a) / 2);

        boxPosition = new Coordinate(selectionBoxXCoord, y);
        innerTextPosition = new Coordinate(innerTextXCoord, y + TOP_PADDING);
    }

    public void render() {
        graphics.setFont(buttonFont);
        renderInnerText();
        renderSelectionBox();
    }

    private void renderInnerText() {
        graphics.setColor(Color.WHITE);
        graphics.drawString(innerText, innerTextPosition.getX(), innerTextPosition.getY());
    }

    private void renderSelectionBox() {
        graphics.setColor(new Color(61, 145, 166));

        graphics.setStroke(new BasicStroke(3));

        graphics.drawRect(boxPosition.getX(), boxPosition.getY(), SELECTION_BOX_WIDTH, SELECTION_BOX_HEIGHT);
    }
}
