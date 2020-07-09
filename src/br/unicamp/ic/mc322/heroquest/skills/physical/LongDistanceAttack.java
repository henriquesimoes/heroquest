package br.unicamp.ic.mc322.heroquest.skills.physical;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.skills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class LongDistanceAttack extends PhysicalSkill {
    public LongDistanceAttack(String skillName, Weapon skilledWeapon) {
        super(skillName, skilledWeapon);
    }

    @Override
    public void updateTargets() {
        Region region = userRegionSelector.getRoomRegion(false);

        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
