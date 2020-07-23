package br.unicamp.ic.mc322.heroquest.walker.skills.physical;

import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.DisplayTargetsMode;
import br.unicamp.ic.mc322.heroquest.walker.skills.PhysicalSkill;

public class ThrowWeaponAttack extends PhysicalSkill {
    private static final String DESCRIPTION = "Attacks the enemy by throwing the weapon";

    public ThrowWeaponAttack(String skillName, Weapon skilledWeapon) {
        super(skillName, DESCRIPTION, DisplayTargetsMode.SHOW_OPTIONS, skilledWeapon);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion(false);

        // Request to the map to visit the region, and if visited unit is a walker, then he is a possible target
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
