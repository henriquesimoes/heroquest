package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.cards;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Card implements Clickable {
    private Graphics2D graphics;
//    private Image imageContent;
    private Rectangle2D boxBounds;
    private BasicStroke boxStroke;
    private int boxHeight;
    private int boxWidth;
    private Coordinate boxPosition;
    private Color boxColor;
    private String title;
    private String description;

    public Card(String title, String description, Graphics2D graphics) {
        this.graphics = graphics;
//        this.imageContent = imageContent;
        this.boxStroke = new BasicStroke(3);
        this.boxColor = Color.WHITE;
        this.boxHeight = 300;
        this.boxWidth = 200;
    }

    public void render(int x, int y) {
        boxPosition = new Coordinate(x, y);

        renderCard();
    }

    private void renderCard() {
        boxBounds = new Rectangle2D.Double(boxPosition.getX(), boxPosition.getY(), boxWidth, boxHeight);

        graphics.setStroke(boxStroke);
        graphics.setColor(boxColor);
        graphics.draw(boxBounds);
    }

    @Override
    public Rectangle2D getBounds() {
        return boxBounds;
    }

    @Override
    public void executeAction() {}
}
