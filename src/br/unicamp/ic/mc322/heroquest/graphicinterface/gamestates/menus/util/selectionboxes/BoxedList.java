package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Settings;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.ScreenStateManager;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Set;

public class BoxedList {
    private final int STANDARD_BOX_WIDTH = 400;
    private int boxHeightBasedInQuantityOfItems = 0;
    private final String[] dataCells;
    private final ArrayList<Clickable> options;
    private final Graphics2D graphics;
    private Coordinate boxPosition;
    private Dimension boxDimension;
    private final BasicStroke boxStroke;
    private final Color boxColor;
    private final Settings SETTINGS;
    private final ScreenStateManager screenStateManager;

    public BoxedList(String[] dataCells, Graphics2D graphics, Settings settings, ScreenStateManager screenStateManager) {
        this.SETTINGS = settings;
        this.screenStateManager = screenStateManager;
        this.graphics = graphics;
        this.options = new ArrayList<>();
        this.dataCells = dataCells;
        this.boxStroke = new BasicStroke(3);
        this.boxColor = Color.WHITE;

        createList();
        setBoxDimension();
    }

    /** Horizontal aligned nn center */
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
            BoxFreeText item = (BoxFreeText) listItem;

            item.render(nextItemPosition);
            nextItemPosition += listItem.getBounds().getHeight() + 20;
        }
    }

    private void renderBox() {
        graphics.setColor(boxColor);
        graphics.setStroke(boxStroke);
        graphics.drawRect(boxPosition.getX(), boxPosition.getY() , boxDimension.getWidth(), boxDimension.getHeight());
    }

    private void createList() {
        Rectangle2D itemBounds;

        for (String item : dataCells) {
            BoxFreeText toGraphics = new BoxFreeText(item, graphics, SETTINGS, screenStateManager);
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
