package br.unicamp.ic.mc322.heroquest.walker.managers;

public abstract class UseItemAction implements Action {
    @Override
    public String getDescription() {
        return "Use items";
    }

    @Override
    public abstract boolean execute();
}
