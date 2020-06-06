package br.unicamp.ic.mc322.heroquest.skills.physicalSkill;

import br.unicamp.ic.mc322.heroquest.item.baseitems.DurableItem;
import br.unicamp.ic.mc322.heroquest.skills.Skill;

public abstract class PhysicalSkill extends Skill {
    private int attackDistance;

    public PhysicalSkill(String skillName, DurableItem skilledItem, int attackDistance) {
        super(skillName, skilledItem);
        this.attackDistance = attackDistance;
    }

    public int getAttackDistance() {
        return attackDistance;
    }

    public void useSkill() {
        DurableItem weapon = (DurableItem) getSkilledItem();
        weapon.updateItemDurability(weapon.getItemDurability() - 1);
    }

}
