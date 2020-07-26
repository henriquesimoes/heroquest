package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.States;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CellMap implements Clickable {
    int x, y;
    Rectangle2D rectangle2D;
    GraphicMapViewer graphicMapViewer;

    CellMap(GraphicMapViewer graphicMapViewer, int i, int j, int width, int height) {
        this.graphicMapViewer = graphicMapViewer;
        rectangle2D = new Rectangle(graphicMapViewer.getX(j), graphicMapViewer.getY(i), width, height);
        this.x = j;
        this.y = i;
    }

    @Override
    public Rectangle2D getBounds() {
        return rectangle2D;
    }

    @Override
    public States executeAction() {
        graphicMapViewer.changeState(x, y);
        return States.STABLE;
    }
}
