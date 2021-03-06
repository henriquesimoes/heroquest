package br.unicamp.ic.mc322.heroquest.engine.gui.states.gamerunning;

import br.unicamp.ic.mc322.heroquest.engine.gui.Clickable;
import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.util.commands.MapCellClickCommand;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClickableCell implements Clickable {
    private final int x, y;
    private final Rectangle2D rectangle2D;
    private MapCellClickCommand cellClickCommand;

    ClickableCell(GraphicGameViewer graphicGameViewer, GamePanel gamePanel, int i, int j, int width, int height) {
        rectangle2D = new Rectangle(graphicGameViewer.getX(j), graphicGameViewer.getY(i), width, height);
        this.x = j;
        this.y = i;
        this.cellClickCommand = new MapCellClickCommand(x, y, graphicGameViewer, gamePanel);
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
