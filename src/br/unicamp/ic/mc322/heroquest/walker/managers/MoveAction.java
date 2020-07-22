package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

public abstract class MoveAction implements Action {
    private final WalkerManager manager;

    public MoveAction(WalkerManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDescription() {
        return "Execute movement";
    }

    @Override
    public abstract boolean execute();
}
