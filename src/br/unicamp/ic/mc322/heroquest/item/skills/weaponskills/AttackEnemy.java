package br.unicamp.ic.mc322.heroquest.item.skills.weaponskills;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.baseitems.DurableItem;

public class AttackEnemy extends PhysicalSkill{

    public AttackEnemy(String skillName, DurableItem skilledItem, int attackDistance) {
        super(skillName, skilledItem, attackDistance);
    }
}
