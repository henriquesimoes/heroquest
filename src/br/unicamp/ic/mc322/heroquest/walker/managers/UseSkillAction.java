package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

public abstract class UseSkillAction implements Action {
    private final WalkerManager walkerManager;

    public UseSkillAction(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
    }

    @Override
    public String getDescription() {
        return "Use skill";
    }

    @Override
    public boolean execute() {
        Walker walker = walkerManager.getWalker();
        Skill[] skills = walker.getSkills();
        Skill chosenSkill = chooseSkill(skills);

        if (chosenSkill == null)
            return false;

        MapObject[] targets = chosenSkill.getTargets();
        MapObject target = walkerManager.chooseTarget(targets);

        if (target == null)
            return false;

        chosenSkill.useSkill(walker, target);

        return true;
    }

    protected abstract Skill chooseSkill(Skill[] skills);
}
