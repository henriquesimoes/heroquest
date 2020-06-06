package br.unicamp.ic.mc322.heroquest.skills.physicalSkill;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class PhysicalSkill extends Skill {

    protected Weapon skilledWeapon;

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;
        int attackIntensity = summoner.getPhysicalAttackPower(skilledWeapon);
        targetWalker.defendsPhysicalSkill(attackIntensity);

        skilledWeapon.updateItemDurability(skilledWeapon.getItemDurability() - 1);
        if (!skilledWeapon.getExistenceState())
            summoner.destroyItem(skilledWeapon);
    }

    public PhysicalSkill(String skillName, Weapon skilledWeapon) {
        super(skillName);
        this.skilledWeapon = skilledWeapon;
    }
}
