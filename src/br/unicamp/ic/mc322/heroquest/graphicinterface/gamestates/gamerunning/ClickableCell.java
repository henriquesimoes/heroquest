package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClickableCell implements Clickable {
    private final int x, y;
    private final Rectangle2D rectangle2D;
    private final GraphicIO graphicIO;

    ClickableCell(GraphicGameViewer graphicGameViewer, int i, int j, int width, int height) {
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
    public State executeAction() {
        graphicIO.changeState(x, y);
        return State.STABLE;
    }
}
