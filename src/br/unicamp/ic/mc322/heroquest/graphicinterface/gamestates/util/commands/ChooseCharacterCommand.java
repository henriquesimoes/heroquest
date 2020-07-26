package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GamePanel;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.State;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

public class ChooseCharacterCommand implements Command {
    private GamePanel gamePanel;
    private HeroKind heroKind;

    public ChooseCharacterCommand(HeroKind heroKind, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.heroKind = heroKind;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        StateManager stateManager = gamePanel.getStateManager();

        gamePanel.setHeroKind(heroKind);
        stateManager.changeState(State.GAME_RUNNING);
    }
}
