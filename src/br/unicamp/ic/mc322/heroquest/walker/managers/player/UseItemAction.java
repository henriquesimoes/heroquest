package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.managers.WalkerPlayer;

public class UseItemAction implements Action {
    private WalkerManager walkerManager;

    public UseItemAction(WalkerPlayer walkerManager) {
        this.walkerManager = walkerManager;
    }

    @Override
    public String getDescription() {
        return "Use items";
    }

    @Override
    public boolean execute() {
        return walkerManager.useItem();
    }
}
