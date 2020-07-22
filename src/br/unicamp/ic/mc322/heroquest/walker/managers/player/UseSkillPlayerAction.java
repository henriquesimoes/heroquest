package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseSkillAction;
import br.unicamp.ic.mc322.heroquest.walker.skills.Skill;

public class UseSkillPlayerAction extends UseSkillAction {
    private WalkerPlayer walkerPlayer;
    private Walker walker;

    UseSkillPlayerAction(WalkerPlayer walkerPlayer) {
        this.walkerPlayer = walkerPlayer;
        this.walker = walkerPlayer.getWalker();
    }

    @Override
    public boolean execute() {
        Skill chosenSkill = chooseSkill();

        if (chosenSkill == null)
            return false;

        MapObject target = chooseTarget(chosenSkill);

        if (target == null)
            return false;

        chosenSkill.useSkill(walker, target);

        return true;
    }

    private Skill chooseSkill() {
        Skill[] skills = walker.getSkillList();
        return (Skill) walkerPlayer.chooseDescribable(skills, "Choose a skill to use:");
    }

    private MapObject chooseTarget(Skill skill) {
        MapObject[] targets = skill.getTargets();

        switch (skill.getDisplayTargetsMode()) {
            case SHOW_OPTIONS:
                return walkerPlayer.chooseTarget(targets);
            case GET_COORDINATE:
                return walkerPlayer.chooseTargetByCoordinate(targets);
            default:
                throw new IllegalStateException("The target display mode is not valid!");
        }
    }
}
