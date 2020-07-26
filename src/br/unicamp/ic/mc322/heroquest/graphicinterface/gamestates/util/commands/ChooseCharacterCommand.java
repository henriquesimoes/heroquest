package br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.util.commands;

import br.unicamp.ic.mc322.heroquest.engine.Command;
import br.unicamp.ic.mc322.heroquest.graphicinterface.GraphicEngine;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.StateManager;
import br.unicamp.ic.mc322.heroquest.graphicinterface.gamestates.States;
import br.unicamp.ic.mc322.heroquest.walker.heroes.HeroKind;

public class ChooseCharacterCommand implements Command {
    private GraphicEngine graphicEngine;
    private HeroKind heroKind;

    public ChooseCharacterCommand(HeroKind heroKind, GraphicEngine graphicEngine) {
        this.graphicEngine = graphicEngine;
        this.heroKind = heroKind;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        StateManager stateManager = graphicEngine.getStateManager();

        graphicEngine.setHeroKind(heroKind);
        stateManager.changeState(States.GAME_RUNNING);
    }
}
