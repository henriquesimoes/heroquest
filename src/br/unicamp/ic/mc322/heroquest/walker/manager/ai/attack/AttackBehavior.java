package br.unicamp.ic.mc322.heroquest.walker.manager.ai.attack;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.Behavior;

import java.util.ArrayList;

public abstract class AttackBehavior extends Behavior {
    public abstract MapObject chooseTarget(ArrayList<MapObject> targets);

    public abstract Skill chooseSkill(ArrayList<Skill> skills);
}
