package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.menus.util.selectionboxes;

import br.unicamp.ic.mc322.heroquest.graphicinterface.GameWindow;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Dimension;

import java.awt.*;
import java.util.ArrayList;

public class BoxedList <T>{
    private final int STANDARD_BOX_WIDTH = 400;
    private ArrayList<T> dataCells;
    private Graphics2D graphics;
    private int fontSize;
    private Coordinate boxPosition;
    private Dimension boxDimension;
    private BasicStroke boxStroke;
    private Color boxColor;

    public BoxedList(ArrayList<T> dataCells, Graphics2D graphics) {
        this.dataCells = dataCells;
        this.fontSize = 16;
        this.boxStroke = new BasicStroke(3);
        this.boxColor = Color.WHITE;

        setBoxDimension();
    }

    /** Horizontal aligned nn center */
    public void render(int y) {
        setBoxPosition(y);
        renderBox();
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
        boxDimension = new Dimension(STANDARD_BOX_WIDTH, fontSize * 12);
    }
}
