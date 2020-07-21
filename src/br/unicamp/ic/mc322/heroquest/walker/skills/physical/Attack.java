package br.unicamp.ic.mc322.heroquest.walker.skills.physical;

import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.skills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Attack extends PhysicalSkill {
    public Attack(String skillName, Weapon skilledWeapon) {
        super(skillName, skilledWeapon);
    }

    @Override
    public void updateTargets() {
        Region region;

        if (skilledWeapon.canAttackDiagonally())
            region = getUserRegionSelector().getAdjacentRegion(false);
        else
            region = getUserRegionSelector().getCardinalRegion(false);

        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
