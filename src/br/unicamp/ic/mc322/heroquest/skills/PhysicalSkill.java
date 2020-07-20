package br.unicamp.ic.mc322.heroquest.skills;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class PhysicalSkill extends Skill {
    protected Weapon skilledWeapon;

    public PhysicalSkill(String skillName, String skillDescription, Weapon skilledWeapon) {
        super(skillName, skillDescription);
        this.skilledWeapon = skilledWeapon;
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;
        int attackIntensity = summoner.getPhysicalAttackPower(skilledWeapon);
        targetWalker.defendsPhysicalSkill(attackIntensity);
        degradeWeaponByUse(summoner);
    }

    protected void degradeWeaponByUse(Walker summoner) {
        skilledWeapon.degradeByUse(summoner);
    }
}
