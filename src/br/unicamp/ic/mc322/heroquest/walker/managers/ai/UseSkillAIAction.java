package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseSkillAction;

import java.util.ArrayList;

public class UseSkillAIAction extends UseSkillAction {
    private WalkerAI walkerAI;

    UseSkillAIAction(WalkerAI walkerAI) {
        super(walkerAI);
        this.walkerAI = walkerAI;
    }

    protected Skill chooseSkill(Skill[] skills) {
        ArrayList<Skill> skillWithValidTargets = new ArrayList<>();

        // store in skillTarget each skill with a valid target
        for (Skill skill : skills) {
            MapObject[] targets = skill.getTargets();
            if (targets.length != 0)
                skillWithValidTargets.add(skill);
        }

        return skillWithValidTargets.size() == 0 ? null : walkerAI.getAttackBehavior().chooseSkill(skillWithValidTargets.toArray(new Skill[0]));
    }
}
