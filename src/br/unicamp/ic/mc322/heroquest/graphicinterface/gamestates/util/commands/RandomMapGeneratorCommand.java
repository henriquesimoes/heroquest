package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;

public class RandomMapGeneratorCommand implements Command {
    private GraphicEngine graphicEngine;

    public RandomMapGeneratorCommand(GraphicEngine graphicEngine) {
        this.graphicEngine = graphicEngine;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        StateManager stateManager = graphicEngine.getStateManager();
        String nickName = JOptionPane.showInputDialog("Choose nickname");

        if (nickName == null)
            nickName = "Player";

        graphicEngine.setMap(new MapManager().generate());
        graphicEngine.setName(nickName);
        stateManager.changeState(States.CHOOSE_CHARACTER);
    }
}
