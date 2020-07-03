package br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

import java.util.ArrayList;

public class Bloodthirsty extends AttackBehavior {
    @Override
    public MapObject chooseTarget(ArrayList<MapObject> targets) {
        return targets.get(Randomizer.nextInt(targets.size()));
    }

    @Override
    public Skill chooseSkill(ArrayList<Skill> skills) {
        return skills.get(Randomizer.nextInt(skills.size()));
    }
}
