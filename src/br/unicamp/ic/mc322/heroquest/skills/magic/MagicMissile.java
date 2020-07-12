package br.unicamp.ic.mc322.heroquest.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class MagicMissile extends MagicSkill implements AbstractMapObjectVisitor {
    private static final int TOTAL_DAMAGE = 6;

    public MagicMissile() {
        super("Magic Missile",
                "Three magic arrows are thrown against the target," +
                        " with " + TOTAL_DAMAGE / 3 + " damage each");
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker)targetObject;
        if (summoner.attemptMagicalMovement())
            targetWalker.defendFromMagicSkill(TOTAL_DAMAGE);
    }

    @Override
    public void updateTargets() {
        // TODO: discover how to set the distance to catch the visible walkers
        Region region = getUserRegionSelector().getRoomRegion(false);
        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(skillUser))
            targets.add(walker);
    }
}
