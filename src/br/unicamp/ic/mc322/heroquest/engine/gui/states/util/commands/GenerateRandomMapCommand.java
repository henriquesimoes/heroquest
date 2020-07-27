package br.unicamp.ic.mc322.heroquest.engine.gui.states.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.engine.gui.GamePanel;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.State;
import br.unicamp.ic.mc322.heroquest.engine.gui.states.StateManager;
import br.unicamp.ic.mc322.heroquest.map.MapManager;

import javax.swing.*;

public class GenerateRandomMapCommand implements Command {
    private GamePanel gamePanel;

    public GenerateRandomMapCommand(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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

        gamePanel.setMap(new MapManager().generate());
        gamePanel.setHeroName(nickName);
        stateManager.changeState(State.CHOOSE_CHARACTER);
    }
}
