package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicSkill;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class SimpleHeal extends MagicSkill {
    public SimpleHeal() {
        super("Simple Heal spell", "Cura um valor de 1 a 6 pontos vida. " +
                "O valor é o número obtido após o lançamento de um dado de seis faces");
    }

    public void useSkill(VisibleMap visibleMap, Walker userWalker, MapObject targetObject) {
        Walker walkerTarget = (Walker)targetObject;

        if (userWalker.attemptMagicalMovement())
            walkerTarget.restoreBodyPoints(userWalker.rollRedDice());
    }

    @Override
    public ArrayList<MapObject> getTargets(Walker reference, VisibleMap visibleMap) {
        Distance distance = null;
        // TODO: configure distance
        ArrayList<Walker> friends = getFriendsWithinArea(reference, visibleMap, distance);
        return arrayListWalkerToMapObject(friends);
    }
}