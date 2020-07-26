package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;

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
        stateManager.changeState(States.MAP_SELECTION);
    }
}
