package br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;

import java.util.ArrayList;

public abstract class AttackBehavior {
    public abstract int chooseTarget(ArrayList<MapObject> targets);

    public abstract int chooseSkill(ArrayList<Skill> skills);
}
