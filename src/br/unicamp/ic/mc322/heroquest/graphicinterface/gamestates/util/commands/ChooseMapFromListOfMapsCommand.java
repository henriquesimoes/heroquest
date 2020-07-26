package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.State;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;
import java.io.FileNotFoundException;

public class ChooseMapFromListOfMapsCommand implements Command {
    private GamePanel gamePanel;
    private String mapName;

    public ChooseMapFromListOfMapsCommand(String mapName, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapName = mapName;
    }


    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        StateManager stateManager = gamePanel.getStateManager();
        String nickName = JOptionPane.showInputDialog("Choose nickname");

        if (nickName == null)
            nickName = "Player";

        try {
            gamePanel.setMap(new MapManager().load(mapName));
            gamePanel.setHeroName(nickName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        stateManager.changeState(State.CHOOSE_CHARACTER);
    }
}
