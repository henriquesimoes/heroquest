package br.unicamp.ic.mc322.heroquest.walker.skills.physical;

import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.DisplayTargetsMode;
import br.unicamp.ic.mc322.heroquest.walker.skills.PhysicalSkill;

public class ThrowProjectileAttack extends PhysicalSkill {
    private static final String DESCRIPTION = "Throw a project at the enemy";

    public ThrowProjectileAttack(String skillName, Weapon skilledWeapon) {
        super(skillName, DESCRIPTION, DisplayTargetsMode.SHOW_OPTIONS, skilledWeapon);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion();

        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
