package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.managers.Action;

import java.util.Set;

public class InteractPlayerAction implements Action {
    private final WalkerPlayer walkerPlayer;

    InteractPlayerAction(WalkerPlayer walkerPlayer) {
        this.walkerPlayer = walkerPlayer;
    }

    @Override
    public String getDescription() {
        return "Interact with objects";
    }

    @Override
    public boolean execute() {
        Set<MapObject> objectsAdjacent = walkerPlayer.getObjectsAdjacent();
        objectsAdjacent.clear();

        Region region = walkerPlayer.getRegionSelector().getAdjacentRegion(false);
        walkerPlayer.accept(walkerPlayer, region);

        MapObject[] arrayObjects = objectsAdjacent.toArray(new MapObject[0]);
        MapObject chosenTarget = walkerPlayer.chooseTarget(arrayObjects);

        if (chosenTarget != null)
            chosenTarget.interact(walkerPlayer.getWalker());

        return false;
    }
}
