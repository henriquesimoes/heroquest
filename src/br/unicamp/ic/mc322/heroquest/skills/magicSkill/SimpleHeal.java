package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class SimpleHeal extends MagicSkill {
    public SimpleHeal() {
        super("Simple Heal spell",
                "It heals from 1 to 6 health points." +
                        "The healing power depends on the result of a six-face die roll.");
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker walkerTarget = (Walker)targetObject;
        if (summoner.attemptMagicalMovement())
            walkerTarget.restoreBodyPoints(summoner.rollRedDice());
    }

    @Override
    public ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager) {
        RegionSelector regionSelector = currentWalkerManager.getRegionSelector();
        // TODO: discover how to set the distance to catch the visible walkers
        Region region = regionSelector.getRoomRegion(false);
        ArrayList<Walker> friends = currentWalkerManager.getFriendsWithinArea(region);
        return currentWalkerManager.arrayListWalkerToMapObject(friends);
    }
}