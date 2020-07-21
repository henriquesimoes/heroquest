package br.unicamp.ic.mc322.heroquest.walker.managers;

public abstract class MoveAction implements Action {
    @Override
    public String getDescription() {
        return "Execute movement";
    }

    @Override
    public abstract boolean execute();
}
