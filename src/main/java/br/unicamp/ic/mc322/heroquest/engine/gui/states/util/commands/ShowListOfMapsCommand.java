package br.unicamp.ic.mc322.heroquest.engine.gui.states.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.State;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.StateManager;

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
        stateManager.changeState(State.LIST_OF_MAPS);
    }
}
