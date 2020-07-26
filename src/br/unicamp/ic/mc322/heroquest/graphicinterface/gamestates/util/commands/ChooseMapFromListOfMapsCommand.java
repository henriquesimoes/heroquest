package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;
import java.io.FileNotFoundException;

public class ChooseMapFromListOfMapsCommand implements Command {
    private GraphicEngine graphicEngine;
    private String mapName;

    public ChooseMapFromListOfMapsCommand(String mapName, GraphicEngine graphicEngine) {
        this.graphicEngine = graphicEngine;
        this.mapName = mapName;
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

        try {
            graphicEngine.setMap(new MapManager().load(mapName));
            graphicEngine.setHeroName(nickName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        stateManager.changeState(States.CHOOSE_CHARACTER);
    }
}
