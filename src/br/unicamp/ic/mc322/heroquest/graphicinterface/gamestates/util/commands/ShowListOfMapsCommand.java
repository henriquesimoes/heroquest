package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;

public class ShowListOfMapsCommand implements Command {
    private StateManager stateManager;

    public ShowListOfMapsCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        stateManager.changeState(States.LIST_OF_MAPS);
    }
}
