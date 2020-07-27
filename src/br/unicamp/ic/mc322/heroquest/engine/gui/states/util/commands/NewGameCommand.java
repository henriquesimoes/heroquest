package br.unicamp.ic.mc322.heroquest.engine.gui.states.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.State;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.StateManager;

public class NewGameCommand implements Command {
    StateManager stateManager;

    public NewGameCommand(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        stateManager.changeState(State.MAP_SELECTION);
    }
}
