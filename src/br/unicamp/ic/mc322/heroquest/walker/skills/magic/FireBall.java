package br.unicamp.ic.mc322.heroquest.walker.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.skills.DisplayTargetsMode;
import br.unicamp.ic.mc322.heroquest.walker.skills.MagicSkill;

public class FireBall extends MagicSkill {
    private static final int ADJACENT_DAMAGE = 3;
    private static final int DAMAGE_TO_PRIMARY_TARGET = 6;

    private boolean attacking;

    public FireBall() {
        super("Fireball",
                "It causes " + DAMAGE_TO_PRIMARY_TARGET + " units of damage to the target," +
                        " and " + ADJACENT_DAMAGE + " to the adjacent positions", DisplayTargetsMode.SHOW_OPTIONS);

        attacking = false;
    }

    @Override
    public void useSkill(MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;

        if (skillUser.attemptMagicalMovement()) {
            WalkerManager targetManager = targetWalker.getManager();
            RegionSelector regionSelector = targetManager.getRegionSelector();

            Region region = regionSelector.getAdjacentRegion(false);

            targetWalker.defendFromMagicSkill(DAMAGE_TO_PRIMARY_TARGET);

            attacking = true;

            // Request to the map to visit the region, and if visited unit is a walker, then he is a possible main target
            accept(this, region);
        }

        skillUser.removeSkill(this);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion(false);

        attacking = false;

        // Request to the map to visit the region, and if visited unit is a walker, then he is a possible main target
        accept(this, region);
    }

    /**
     * See that if attacking is false, then we are searching the possibles main targets of the skill
     * While if attacking is true, then we are searching the walkers in adjacent positions of the main target
     */
    @Override
    public void visit(Walker walker) {
        if (attacking)
            walker.defendFromMagicSkill(ADJACENT_DAMAGE);
        else if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
