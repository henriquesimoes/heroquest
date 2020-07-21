package br.unicamp.ic.mc322.heroquest.walker.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

public class Teleport extends MagicSkill {
    public Teleport() {
        super("Teleport",
                "The hero or the monster teleports to a visible position");
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        WalkerManager summonerManager = summoner.getManager();
        if (summoner.attemptMagicalMovement())
            summonerManager.moveWalker(targetObject.getPosition());

        summoner.removeSkill(this);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion(true);

        accept(this, region);
    }

    @Override
    public void visit(StructuralObject structuralObject) {
        targets.add(structuralObject);
    }
}
