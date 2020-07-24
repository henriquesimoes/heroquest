package br.unicamp.ic.mc322.heroquest.graphicinterface;

import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.manager.Clickable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CellMap implements Clickable {
    int x, y;
    Rectangle2D rectangle2D;
    GraphicMapViewer graphicMapViewer;

    CellMap(GraphicMapViewer graphicMapViewer, int x, int y, int width, int height) {
        this.graphicMapViewer = graphicMapViewer;
        rectangle2D = new Rectangle(graphicMapViewer.getX(x), graphicMapViewer.getY(y), width, height);
        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle2D getBounds() {
        return rectangle2D;
    }

    @Override
    public void executeAction() {
        graphicMapViewer.changeState(x, y);
    }
}
