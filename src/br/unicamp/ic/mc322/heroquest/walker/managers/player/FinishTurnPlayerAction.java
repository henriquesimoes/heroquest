package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.walker.managers.Action;

public class FinishTurnPlayerAction implements Action {
    private final WalkerPlayer player;

    public FinishTurnPlayerAction(WalkerPlayer walkerPlayer) {
        player = walkerPlayer;
    }

    @Override
    public String getDescription() {
        return "Finish turn";
    }

    @Override
    public boolean execute() {
        player.finishTurn();
        return false;
    }
}
