package br.unicamp.ic.mc322.heroquest.item.skills.weaponskills;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.item.baseitems.DurableItem;
import br.unicamp.ic.mc322.heroquest.item.skills.Skill;

public class PhysicalSkill extends Skill {
    private int attackDistance;

    public PhysicalSkill(String skillName, DurableItem skilledItem, int attackDistance) {
        super(skillName, skilledItem);
        this.attackDistance = attackDistance;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    @Override
    public void useSkill() {
        DurableItem weapon = (DurableItem) getSkilledItem();
        weapon.updateItemDurability(weapon.getItemDurability() - 1);
    }
}
