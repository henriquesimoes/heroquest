package br.unicamp.ic.mc322.heroquest.walker.managers;

public abstract class UseSkillAction implements Action {
    @Override
    public String getDescription() {
        return "Use skill";
    }

    public abstract boolean execute();
}
