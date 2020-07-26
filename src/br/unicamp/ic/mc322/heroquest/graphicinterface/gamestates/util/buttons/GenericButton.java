package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GenericButton {
    private final BasicStroke boxStroke;
    private final String innerText;
    private final Graphics2D graphics;
    private int selectionBoxWidth;
    private int selectionBoxHeight;
    private Color boxColor;
    private Coordinate boxPosition;
    private Rectangle2D boxBounds;
    private Coordinate innerTextPosition;
    private Font font;

    public GenericButton(String text, Graphics2D graphics) {
        this.graphics = graphics;
        this.innerText = text;
        this.selectionBoxWidth = 100;
        this.selectionBoxHeight = 50;
        this.boxColor = Color.WHITE;
        this.boxStroke = new BasicStroke(3);
        this.font = new Font("Helvetica", Font.BOLD, 16);
    }

    public void render() {
        renderText();
        renderSelectionBox();
    }

    private void renderText() {
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(innerText, innerTextPosition.getX(), innerTextPosition.getY());
    }

    private void renderSelectionBox() {
        graphics.setColor(boxColor);

        graphics.setStroke(boxStroke);
        boxBounds = new Rectangle2D.Double(boxPosition.getX(), boxPosition.getY(), selectionBoxWidth, selectionBoxHeight);

        graphics.draw(boxBounds);
    }

    public Rectangle2D getBoxBounds() {
        return boxBounds;
    }

    public void setBoxColor(Color color) {
        this.boxColor = color;
    }

    public void setBoxDimensions(Dimension dimensions) {
        selectionBoxWidth = dimensions.getWidth();
        selectionBoxHeight = dimensions.getHeight();
    }

    public void setFontSize(int fontSize) {
        font = new Font("Helvetica", Font.BOLD, fontSize);
    }

    /**
     * Positioned with horizontal alignment in center
     */
    public void setPosition(int y) {
        int x = (GameWindow.WINDOW_WIDTH - selectionBoxWidth) / 2;
        boxPosition = new Coordinate(x, y);

        alignInnerTextOnButtonCenter();
    }

    private void alignInnerTextOnButtonCenter() {
        Rectangle2D innerTextBounds = font.getStringBounds(innerText, graphics.getFontRenderContext());
        int x = boxPosition.getX() + (selectionBoxWidth - (int) innerTextBounds.getWidth()) / 2;
        int y = boxPosition.getY() + selectionBoxHeight - (int) innerTextBounds.getHeight() + 5;

        innerTextPosition = new Coordinate(x, y);
    }
}
