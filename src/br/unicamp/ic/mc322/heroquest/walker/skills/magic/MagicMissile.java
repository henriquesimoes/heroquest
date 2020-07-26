package br.unicamp.ic.mc322.heroquest.walker.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.skills.DisplayTargetsMode;
import br.unicamp.ic.mc322.heroquest.walker.skills.MagicSkill;

public class MagicMissile extends MagicSkill {
    private static final int TOTAL_DAMAGE = 6;

    public MagicMissile() {
        super("Magic Missile",
                "Three magic arrows are thrown against the target," +
                        " with " + TOTAL_DAMAGE / 3 + " damage each", DisplayTargetsMode.SHOW_OPTIONS);
    }

    @Override
    public void useSkill(MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;
        if (tryToUseMagicSkill())
            targetWalker.defendFromMagicSkill(skillUser.getName(), TOTAL_DAMAGE);

        skillUser.removeSkill(this);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion();

        // Request to the map to visit the region, and if the visited unit has a walker, then it is a possible target
        use(region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
