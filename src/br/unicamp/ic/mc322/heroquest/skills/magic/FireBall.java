package br.unicamp.ic.mc322.heroquest.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

public class FireBall extends MagicSkill {
    private static final int ADJACENT_DAMAGE = 3;
    private static final int DAMAGE_TO_PRIMARY_TARGET = 6;

    private boolean attacking;

    public FireBall() {
        super("Fireball",
                "It causes " + DAMAGE_TO_PRIMARY_TARGET + " units of damage to the target," +
                        " and " + ADJACENT_DAMAGE + " to the adjacent positions");

        attacking = false;
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;

        if (summoner.attemptMagicalMovement()) {
            WalkerManager targetManager = targetWalker.getManager();
            RegionSelector regionSelector = targetManager.getRegionSelector();

            Region region = regionSelector.getAdjacentRegion(false);

            targetWalker.defendFromMagicSkill(DAMAGE_TO_PRIMARY_TARGET);

            attacking = true;
            accept(this, region);
        }

        summoner.removeSkill(this);
    }

    @Override
    public void updateTargets() {
        // TODO: discover how to set the distance to catch the visible walkers
        Region region = getUserRegionSelector().getVisibleRegion(false);

        attacking = false;
        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (attacking)
            walker.defendFromMagicSkill(ADJACENT_DAMAGE);
        else if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
