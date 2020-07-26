package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning.GraphicGameViewer;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.gamerunning.GraphicIO;

public class MapCellClickCommand implements Command {
    private GraphicGameViewer graphicGameViewer;
    private GraphicEngine graphicEngine;
    private int x;
    private int y;

    public MapCellClickCommand(int x, int y, GraphicGameViewer graphicGameViewer, GraphicEngine graphicEngine) {
        this.graphicEngine = graphicEngine;
        this.graphicGameViewer = graphicGameViewer;
        this.x = x;
        this.y = y;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        GraphicIO graphicIO = graphicGameViewer.getGraphicIO();
        StateManager stateManager = graphicEngine.getStateManager();

        graphicIO.changeState(x, y);
        stateManager.changeState(States.STABLE);

    }
}
