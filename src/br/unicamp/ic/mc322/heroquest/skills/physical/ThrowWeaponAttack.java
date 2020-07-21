package br.unicamp.ic.mc322.heroquest.skills.physical;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.skills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class ThrowWeaponAttack extends PhysicalSkill {
    private static final String DESCRIPTION = "Attacks the enemy by throwing the weapon";

    public ThrowWeaponAttack(String skillName, Weapon skilledWeapon) {
        super(skillName, DESCRIPTION, skilledWeapon);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion(false);

        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(skillUser))
            targets.add(walker);
    }

    @Override
    protected void degradeWeaponByUse(Walker summoner) {
        summoner.destroyItem(skilledWeapon);
    }
}
