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

        if (tryUseMagicSkill()) {
            WalkerManager targetManager = targetWalker.getManager();
            RegionSelector regionSelector = targetManager.getRegionSelector();

            Region region = regionSelector.getAdjacentRegion(false);

            targetWalker.defendFromMagicSkill(skillUser.getName(), DAMAGE_TO_PRIMARY_TARGET);

            attacking = true;
            accept(this, region);
        }

        skillUser.removeSkill(this);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion();

        attacking = false;
        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (attacking)
            walker.defendFromMagicSkill(skillUser.getName(), ADJACENT_DAMAGE);
        else if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
