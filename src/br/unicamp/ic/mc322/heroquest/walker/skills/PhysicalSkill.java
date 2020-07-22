package br.unicamp.ic.mc322.heroquest.walker.skills;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;

public abstract class PhysicalSkill extends Skill {
    protected Weapon skilledWeapon;

    public PhysicalSkill(String skillName, String skillDescription, DisplayTargetsMode displayTargetsMode, Weapon skilledWeapon) {
        super(skillName, skillDescription, displayTargetsMode);
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
