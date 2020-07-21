package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BoxedList {
    private final int STANDARD_BOX_WIDTH = 400;
    private int boxHeightBaseInQuantityOfItems = 0;
    private String[] dataCells;
    private Graphics2D graphics;
    private int fontSize;
    private Coordinate boxPosition;
    private Dimension boxDimension;
    private BasicStroke boxStroke;
    private Color boxColor;

    public BoxedList(String[] dataCells, Graphics2D graphics) {
        this.graphics = graphics;
        this.dataCells = dataCells;
        this.fontSize = 16;
        this.boxStroke = new BasicStroke(3);
        this.boxColor = Color.WHITE;

        setBoxDimension();
    }

    /** Horizontal aligned nn center */
    public void render(int y) {
        setBoxPosition(y);
        renderListItems();
        setBoxDimension();
        renderBox();
    }

    private void renderListItems() {
        int nextItemPosition = boxPosition.getY();
        Rectangle2D itemBounds;

        for (String item : dataCells) {
            BoxFreeText toGraphics = new BoxFreeText(item, graphics);
            itemBounds = toGraphics.getTextBounds();
            nextItemPosition += itemBounds.getHeight() + 10;
            boxHeightBaseInQuantityOfItems += itemBounds.getHeight();

            toGraphics.render(nextItemPosition);
        }
    }

    private void renderBox() {
        graphics.setColor(boxColor);
        graphics.setStroke(boxStroke);
        graphics.drawRect(boxPosition.getX(), boxPosition.getY() , boxDimension.getWidth(), boxDimension.getHeight());
    }

    private void setBoxPosition(int y) {
        int x = (GameWindow.WINDOW_WIDTH - boxDimension.getWidth()) / 2;

        boxPosition = new Coordinate(x, y);
    }

    private void setBoxDimension() {
        boxDimension = new Dimension(STANDARD_BOX_WIDTH, boxHeightBaseInQuantityOfItems + 20 * dataCells.length);
    }
}
