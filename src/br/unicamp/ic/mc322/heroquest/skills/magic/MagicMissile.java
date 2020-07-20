package br.unicamp.ic.mc322.heroquest.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class MagicMissile extends MagicSkill {
    private static final int TOTAL_DAMAGE = 6;

    public MagicMissile() {
        super("Magic Missile",
                "Three magic arrows are thrown against the target," +
                        " with " + TOTAL_DAMAGE / 2 + " damage each");
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker) targetObject;
        if (summoner.attemptMagicalMovement())
            targetWalker.defendFromMagicSkill(TOTAL_DAMAGE);

        summoner.removeSkill(this);
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
