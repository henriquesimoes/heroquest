package br.unicamp.ic.mc322.heroquest.walker.managers.ai.attack;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.AttackBehavior;

public class Bloodthirsty extends AttackBehavior {
    @Override
    public MapObject chooseTarget(MapObject[] targets) {
        return targets[Randomizer.nextInt(targets.length)];
    }

    @Override
    public Skill chooseSkill(Skill[] skills) {
        return skills[Randomizer.nextInt(skills.length)];
    }
}
