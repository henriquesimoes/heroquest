package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.engine.GameMonitor;
import br.unicamp.ic.mc322.heroquest.engine.IOInterface;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;

public class QuitPlayerAction implements Action {
    private final IOInterface io;
    private final WalkerPlayer player;

    public QuitPlayerAction(WalkerPlayer walkerPlayer) {
        this.player = walkerPlayer;
        this.io = walkerPlayer.getIOInterface();
    }

    @Override
    public String getDescription() {
        return "Quit";
    }

    @Override
    public boolean execute() {
        boolean quit = io.getBooleanAnswer("Are you sure you want to quit?");

        if (quit) {
            GameMonitor monitor = GameMonitor.getInstance();

            player.finishTurn();
            monitor.notifyDeath(player.getWalker());
        }

        return false;
    }
}
