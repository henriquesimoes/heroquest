package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.managers.WalkerPlayer;

import java.util.ArrayList;
import java.util.Set;

public class InteractAction implements Action {
    private WalkerPlayer walkerPlayer;

    public InteractAction(WalkerPlayer walkerPlayer) {
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

        ArrayList<MapObject> arrayObjects = new ArrayList<>(objectsAdjacent);
        MapObject chosenTarget = walkerPlayer.chooseTarget(arrayObjects);

        if (chosenTarget != null)
            chosenTarget.interact(walkerPlayer.getWalker());

        return false;
    }
}
