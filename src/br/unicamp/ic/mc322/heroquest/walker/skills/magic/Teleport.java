package br.unicamp.ic.mc322.heroquest.walker.skills.magic;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.skills.DisplayTargetsMode;
import br.unicamp.ic.mc322.heroquest.walker.skills.MagicSkill;

public class Teleport extends MagicSkill {
    public Teleport() {
        super("Teleport",
                "The character teleports to a visible position", DisplayTargetsMode.GET_COORDINATE);
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
        Region region = getUserRegionSelector().getVisibleRegion();

        // Request to the map to visit the region, and if the visited unit is a walkable, then it is a possible target
        use(region);
    }

    @Override
    public void visit(StructuralObject structuralObject) {
        if (structuralObject.isAllowedToWalkOver())
            targets.add(structuralObject);
    }

    @Override
    public void visit(FixedObject fixedObject) {
        if (fixedObject.isAllowedToWalkOver())
            targets.add(fixedObject);
    }
}
