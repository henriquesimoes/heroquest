package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.geom.Ruler;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class SimpleHeal extends MagicSkill {
    public SimpleHeal() {
        super("Simple Heal spell", "Cura um valor de 1 a 6 pontos vida. " +
                "O valor é o número obtido após o lançamento de um dado de seis faces");
    }

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        Walker walkerTarget = (Walker)targetObject;
        if (summoner.attemptMagicalMovement())
            walkerTarget.restoreBodyPoints(summoner.rollRedDice());
    }

    @Override
    public ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager) {
        Ruler ruler = currentWalkerManager.getRuler();
        Distance distance; // TODO: discover how to set the distance to catch the visible walkers
        ArrayList<Walker> friends = currentWalkerManager.getFriendsWithinArea(distance);
        return currentWalkerManager.arrayListWalkerToMapObject(friends);
    }
}