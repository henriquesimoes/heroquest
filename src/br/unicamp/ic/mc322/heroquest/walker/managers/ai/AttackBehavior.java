package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;

public abstract class AttackBehavior extends Behavior {
    protected abstract MapObject chooseTarget(MapObject[] targets);

    protected abstract Skill chooseSkill(Skill[] skills);
}
