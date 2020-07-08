package br.unicamp.ic.mc322.heroquest.skills.physicalSkill;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class PhysicalSkill extends Skill {

    protected Weapon skilledWeapon;

    public PhysicalSkill(String skillName, Weapon skilledWeapon) {
        super(skillName);
        this.skilledWeapon = skilledWeapon;
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;
        int attackIntensity = summoner.getPhysicalAttackPower(skilledWeapon);
        targetWalker.defendsPhysicalSkill(attackIntensity);
        skilledWeapon.degradeByUse(summoner);
    }
}
