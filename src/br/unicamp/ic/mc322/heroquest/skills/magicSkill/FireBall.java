package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class FireBall extends MagicSkill {
    private final int ADJACENT_DAMAGE = 3;
    private final int DAMAGE_TO_PRIMARY_TARGET = 6;

    public FireBall() {
        super("Fireball", "Causa 6 de dano ao alvo. Causa 3 danos nos mobs adjacentes");
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker)targetObject;

        if (summoner.attemptMagicalMovement()) {
            WalkerManager targetManager = targetWalker.getManager();
            RegionSelector regionSelector = targetManager.getRegionSelector();
            Region region = regionSelector.getAdjacentRegion(false);
            ArrayList<Walker> adjacentTargets = targetManager.getEnemiesWithinArea(region);

            targetWalker.defendsMagicSkill(DAMAGE_TO_PRIMARY_TARGET);
            for (Walker target : adjacentTargets)
                target.defendsMagicSkill(ADJACENT_DAMAGE);
        }
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
