package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseSkillAction;
import br.unicamp.ic.mc322.heroquest.walker.skills.Skill;

import java.util.ArrayList;

public class UseSkillAIAction extends UseSkillAction {
    private WalkerAI walkerAI;

    UseSkillAIAction(WalkerAI walkerAI) {
        this.walkerAI = walkerAI;
    }

    @Override
    public boolean execute() {
        Walker walker = walkerAI.getWalker();
        Skill[] skills = walker.getSkillList();
        Skill chosenSkill = chooseSkill(skills);

        if (chosenSkill == null)
            return false;

        MapObject[] targets = chosenSkill.getTargets();
        MapObject target = walkerAI.chooseTarget(targets);

        if (target == null)
            return false;

        chosenSkill.useSkill(target);

        return true;
    }

    protected Skill chooseSkill(Skill[] skills) {
        ArrayList<Skill> skillWithValidTargets = new ArrayList<>();

        // store in skillTarget each skill with a valid target
        for (Skill skill : skills) {
            MapObject[] targets = skill.getTargets();
            if (targets.length != 0)
                skillWithValidTargets.add(skill);
        }

        if (skillWithValidTargets.isEmpty())
            return null;

        return walkerAI.getAttackBehavior().chooseSkill(skillWithValidTargets.toArray(new Skill[0]));
    }
}
