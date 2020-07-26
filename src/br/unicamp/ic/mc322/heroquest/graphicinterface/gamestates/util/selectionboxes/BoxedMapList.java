package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BoxedMapList {
    private final int STANDARD_BOX_WIDTH = 400;
    private final String[] dataCells;
    private final ArrayList<Clickable> options;
    private final Graphics2D graphics;
    private final BasicStroke boxStroke;
    private final Color boxColor;
    private final GraphicEngine graphicEngine;
    private int boxHeightBasedInQuantityOfItems = 0;
    private Coordinate boxPosition;
    private Dimension boxDimension;

    public BoxedMapList(String[] dataCells, Graphics2D graphics, GraphicEngine graphicEngine) {
        this.graphicEngine = graphicEngine;
        this.graphics = graphics;
        this.options = new ArrayList<>();
        this.dataCells = dataCells;
        this.boxStroke = new BasicStroke(3);
        this.boxColor = Color.WHITE;

        createList();
        setBoxDimension();
    }

    /**
     * Horizontal aligned nn center
     */
    public void render(int y) {
        setBoxPosition(y);
        renderListItems();
        setBoxDimension();
        renderBox();
    }

    public ArrayList<Clickable> getOptions() {
        return options;
    }

    private void renderListItems() {
        int nextItemPosition = boxPosition.getY() + 30;

        for (Clickable listItem : options) {
            MapListItem item = (MapListItem) listItem;

            item.render(nextItemPosition);
            nextItemPosition += listItem.getBounds().getHeight() + 20;
        }
    }

    private void renderBox() {
        graphics.setColor(boxColor);
        graphics.setStroke(boxStroke);
        graphics.drawRect(boxPosition.getX(), boxPosition.getY(), boxDimension.getWidth(), boxDimension.getHeight());
    }

    private void createList() {
        Rectangle2D itemBounds;

        for (String item : dataCells) {
            MapListItem toGraphics = new MapListItem(item, graphics, graphicEngine);
            itemBounds = toGraphics.getBounds();
            boxHeightBasedInQuantityOfItems += itemBounds.getHeight();

            options.add(toGraphics);
        }
    }

    private void setBoxPosition(int y) {
        int x = (GameWindow.WINDOW_WIDTH - boxDimension.getWidth()) / 2;

        boxPosition = new Coordinate(x, y);
    }

    private void setBoxDimension() {
        boxDimension = new Dimension(STANDARD_BOX_WIDTH, boxHeightBasedInQuantityOfItems + 20 * (dataCells.length));
    }
}
