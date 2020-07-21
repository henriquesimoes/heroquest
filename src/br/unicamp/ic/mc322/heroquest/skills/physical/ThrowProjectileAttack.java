package br.unicamp.ic.mc322.heroquest.skills.physical;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.skills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class ThrowProjectileAttack extends PhysicalSkill {
    private static final String DESCRIPTION = "Throw a project at the enemy";

    public ThrowProjectileAttack(String skillName, Weapon skilledWeapon) {
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
}
