package br.unicamp.ic.mc322.heroquest.walker.skills;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;

public abstract class PhysicalSkill extends Skill {
    protected Weapon skilledWeapon;
    private String skillNameSuffix;

    public PhysicalSkill(String skillName, String skillDescription, DisplayTargetsMode displayTargetsMode, Weapon skilledWeapon) {
        super(skillName, skillDescription, displayTargetsMode);
        this.skilledWeapon = skilledWeapon;
        this.skillNameSuffix = "";
    }

    @Override
    public void useSkill(MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;
        int attackIntensity = skillUser.getPhysicalAttackPower(skilledWeapon);
        targetWalker.defendsPhysicalSkill(attackIntensity);
        degradeWeaponByUse(skillUser);
    }

    protected void degradeWeaponByUse(Walker summoner) {
        skilledWeapon.degradeByUse(summoner);
    }

    public void setNameSuffix(String suffixName) {
        this.skillNameSuffix = suffixName;
    }

    public void clearNameSuffix() {
        this.skillNameSuffix = "";
    }

    @Override
    public String getName() {
        return skillName + skillNameSuffix;
    }
}
