package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.cards;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.buttons.GenericButton;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Card implements Clickable {
    private Command command;
    Font titleFont;
    Font descriptionFont;
    private Graphics2D graphics;
    private BufferedImage imageContent;
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
        this.boxStroke = new BasicStroke(3);
        this.boxColor = Color.WHITE;
        this.boxHeight = 300;
        this.boxWidth = 200;
        this.title = title;
        this.description = description;
        this.titleFont = new Font("Helvetica", Font.BOLD, 16);
        this.descriptionFont = new Font("Helvetica", Font.BOLD, 14);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

    public void setImageContent(BufferedImage imageContent) {
        this.imageContent = imageContent;
    }

    public void render(int x, int y) {
        boxPosition = new Coordinate(x, y);

        renderCard();
    }

    private void renderCard() {
        boxBounds = new Rectangle2D.Double(boxPosition.getX(), boxPosition.getY(), boxWidth, boxHeight);

        renderCardBox();
        renderContentImage();
        renderTitle();
        renderDescription();
    }

    private void renderTitle() {
        int xCoord = boxPosition.getX() + 12;
        int yCoord = (int) (boxPosition.getY() + 0.67 * boxBounds.getHeight());
        graphics.setFont(titleFont);
        graphics.drawString(title, xCoord, yCoord);
    }

    private void renderDescription() {
        int xCoord = boxPosition.getX() + 12;
        int yCoord = (int) (boxPosition.getY() + 0.75 * boxBounds.getHeight());

        graphics.setFont(descriptionFont);
        drawLines(xCoord, yCoord);
    }

    private void drawLines(int x, int y) {
        String[] stringWithLineBreaks = description.split("\n");

        int yNextLineCoord = 10;

        for (String line : stringWithLineBreaks) {
            graphics.drawString(line, x, y + yNextLineCoord);

            yNextLineCoord += 20;
        }
    }

    private void renderCardBox() {
        graphics.setStroke(boxStroke);
        graphics.setColor(boxColor);
        graphics.draw(boxBounds);
        graphics.drawLine(
                boxPosition.getX(),
                boxPosition.getY() + (int) (0.7 * boxBounds.getHeight()),
                boxPosition.getX() + (int) boxBounds.getWidth(),
                boxPosition.getY() + (int) (0.7 * boxBounds.getHeight()));

    }

    private void renderContentImage() {
        int xCoord = (int) (boxPosition.getX() + (boxBounds.getWidth() - imageContent.getWidth()) / 2);
        graphics.drawImage(imageContent, xCoord, boxPosition.getY() + 30, null);
    }

    /**
     * If the component wasn't rendered, the card will be positioned in coord (0,0)
     */
    @Override
    public Rectangle2D getBounds() {
        if (boxBounds == null) {
            return new Rectangle2D.Double(0, 0, boxWidth, boxHeight);
        }

        return boxBounds;
    }
}
