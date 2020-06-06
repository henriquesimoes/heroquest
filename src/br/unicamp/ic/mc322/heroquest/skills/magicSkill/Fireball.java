package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Fireball extends MagicSkill {
    private final int ADJACENT_DAMAGE = 3;
    private final int DAMAGE_TO_PRIMARY_TARGET = 6;

    public Fireball() {
        super("Fireball", "Causa 6 de dano ao alvo. Causa 3 danos nos mobs adjacentes");
    }

    @Override
    public void useSkill(VisibleMap visibleMap, Walker summoner, MapObject targetObject) {
        Walker targetWalker = (Walker)targetObject;
        if (summoner.attemptMagicalMovement()) {

            Distance distance = null;
            // TODO: configure distance
            ArrayList<Walker> adjacentTargets = getEnemiesWithinArea(targetWalker, visibleMap, distance);

            targetWalker.defendsMagicSkill(DAMAGE_TO_PRIMARY_TARGET);
            for (Walker target : adjacentTargets)
                target.defendsMagicSkill(ADJACENT_DAMAGE);
        }
    }

    @Override
    public ArrayList<MapObject> getTargets(Walker reference, VisibleMap visibleMap) {
        Distance distance = null;
        // TODO: configure distance
        ArrayList<Walker> enemies = getEnemiesWithinArea(reference, visibleMap, distance);
        return arrayListWalkerToMapObject(enemies);
    }
}
