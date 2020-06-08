package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.geom.Ruler;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class Fireball extends MagicSkill {
    private final int ADJACENT_DAMAGE = 3;
    private final int DAMAGE_TO_PRIMARY_TARGET = 6;

    public Fireball() {
        super("Fireball", "Causa 6 de dano ao alvo. Causa 3 danos nos mobs adjacentes");
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker)targetObject;

        if (summoner.attemptMagicalMovement()) {
            WalkerManager targetManager = targetWalker.getManager();
            Ruler ruler = targetManager.getRuler();
            Distance distance = ruler.getAdjacentDistance();
            ArrayList<Walker> adjacentTargets = targetManager.getEnemiesWithinArea(distance);

            targetWalker.defendsMagicSkill(DAMAGE_TO_PRIMARY_TARGET);
            for (Walker target : adjacentTargets)
                target.defendsMagicSkill(ADJACENT_DAMAGE);
        }
    }

    @Override
    public ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager) {
        Ruler ruler = currentWalkerManager.getRuler();
        Distance distance; // TODO: discover how to set the distance to catch the visible walkers
        ArrayList<Walker> enemies = currentWalkerManager.getEnemiesWithinArea(distance);
        return arrayListWalkerToMapObject(enemies);
    }
}
