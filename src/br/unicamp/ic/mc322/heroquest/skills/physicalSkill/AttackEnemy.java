package br.unicamp.ic.mc322.heroquest.skills.physicalSkill;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class AttackEnemy extends PhysicalSkill {
    public AttackEnemy(String skillName, Weapon skilledWeapon) {
        super(skillName, skilledWeapon);
    }

    @Override
    public void updateTargets() {
        Region region;

        if (skilledWeapon.canAttackDiagonally())
            region = userRegionSelector.getAdjacentRegion(false);
        else
            region = userRegionSelector.getCardinalRegion(false);

        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
