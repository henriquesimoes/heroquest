package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class MagicMissile extends MagicSkill {
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
    public ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager) {
        RegionSelector regionSelector = currentWalkerManager.getRegionSelector();
        // TODO: discover how to set the distance to catch the visible walkers
        Region region = regionSelector.getRoomRegion(false);
        ArrayList<Walker> enemies = currentWalkerManager.getEnemiesWithinArea(region);
        return currentWalkerManager.arrayListWalkerToMapObject(enemies);
    }
}