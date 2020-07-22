package br.unicamp.ic.mc322.heroquest.walker.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.skills.DisplayTargetsMode;
import br.unicamp.ic.mc322.heroquest.walker.skills.MagicSkill;

public class SimpleHeal extends MagicSkill {
    public SimpleHeal() {
        super("Simple Heal spell",
                "It heals from 1 to 6 health points." +
                        "The healing power depends on the result of a six-face die roll.", DisplayTargetsMode.SHOW_OPTIONS);
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker walkerTarget = (Walker) targetObject;
        if (summoner.attemptMagicalMovement())
            walkerTarget.restoreBodyPoints(summoner.rollRedDice());

        summoner.removeSkill(this);
    }

    @Override
    public void updateTargets() {
        // TODO: discover how to set the distance to catch the visible walkers
        Region region = getUserRegionSelector().getVisibleRegion(false);
        accept(this, region);
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isFriend(skillUser))
            targets.add(walker);
    }
}