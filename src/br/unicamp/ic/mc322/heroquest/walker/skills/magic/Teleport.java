package br.unicamp.ic.mc322.heroquest.walker.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.skills.DisplayTargetsMode;
import br.unicamp.ic.mc322.heroquest.walker.skills.MagicSkill;

public class Teleport extends MagicSkill {
    public Teleport() {
        super("Teleport",
                "The hero or the monster teleports to a visible position", DisplayTargetsMode.GET_COORDINATE);
    }

    @Override
    public void useSkill(MapObject targetObject) {
        WalkerManager summonerManager = skillUser.getManager();
        if (skillUser.attemptMagicalMovement())
            summonerManager.moveWalker(targetObject.getPosition());

        skillUser.removeSkill(this);
    }

    @Override
    public void updateTargets() {
        Region region = getUserRegionSelector().getVisibleRegion(true);

        // Request to the map to visit the region, and if visited unit is a floor, then it is a possible target
        accept(this, region);
    }

    @Override // note that how the requested region is walkable, then this structural object is a floor
    public void visit(StructuralObject structuralObject) {
        targets.add(structuralObject);
    }
}
