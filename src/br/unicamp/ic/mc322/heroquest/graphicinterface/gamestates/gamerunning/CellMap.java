package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CellMap implements Clickable {
    int x, y;
    Rectangle2D rectangle2D;
    GraphicGameViewer graphicGameViewer;
    GraphicIO graphicIO;

    CellMap(GraphicGameViewer graphicGameViewer, int i, int j, int width, int height) {
        this.graphicGameViewer = graphicGameViewer;
        this.graphicIO = graphicGameViewer.getGraphicIO();

        rectangle2D = new Rectangle(graphicGameViewer.getX(j), graphicGameViewer.getY(i), width, height);
        this.x = j;
        this.y = i;
    }

    @Override
    public Rectangle2D getBounds() {
        return rectangle2D;
    }

    @Override
    public States executeAction() {
        graphicIO.changeState(x, y);
        return States.STABLE;
    }
}
