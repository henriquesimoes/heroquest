package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.Clickable;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands.MapCellClickCommand;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClickableCell implements Clickable {
    private final int x, y;
    private final Rectangle2D rectangle2D;
    private MapCellClickCommand cellClickCommand;

    ClickableCell(GraphicGameViewer graphicGameViewer, GraphicEngine graphicEngine, int i, int j, int width, int height) {
        rectangle2D = new Rectangle(graphicGameViewer.getX(j), graphicGameViewer.getY(i), width, height);
        this.x = j;
        this.y = i;
        this.cellClickCommand = new MapCellClickCommand(x, y, graphicGameViewer, graphicEngine);
    }

    @Override
    public Rectangle2D getBounds() {
        return rectangle2D;
    }

    @Override
    public void executeAction() {
        cellClickCommand.execute();
    }
}
