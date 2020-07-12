package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;

import java.util.ArrayList;

public abstract class AttackBehavior extends Behavior {
    public abstract MapObject chooseTarget(ArrayList<MapObject> targets);

    public abstract Skill chooseSkill(ArrayList<Skill> skills);
}
