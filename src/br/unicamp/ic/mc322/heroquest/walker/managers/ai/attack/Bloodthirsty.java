package br.unicamp.ic.mc322.heroquest.walker.managers.ai.attack;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.AttackBehavior;
import br.unicamp.ic.mc322.heroquest.walker.skills.Skill;

public class Bloodthirsty extends AttackBehavior {
    @Override
    protected MapObject chooseTarget(MapObject[] targets) {
        return targets[Randomizer.nextInt(targets.length)];
    }

    @Override
    protected Skill chooseSkill(Skill[] skills) {
        return skills[Randomizer.nextInt(skills.length)];
    }
}
