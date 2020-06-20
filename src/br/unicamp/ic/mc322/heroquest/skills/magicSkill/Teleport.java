package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class Teleport extends MagicSkill {
    public Teleport() {
        super("Teleport",
                "The hero or the monster teleports to a visible position");
    }

    /**TODO: método apenas para teste, deve ser implementado no package do mapa*/
    public Coordinate showCanvasToPickPosition() {return new Coordinate(0, 0);}

    @Override
    public void useSkill(Walker summoner, MapObject targetObject) {
        WalkerManager summonerManager = summoner.getManager();
        if (summoner.attemptMagicalMovement())
            summonerManager.moveWalker(targetObject.getPosition());
    }

    @Override
    public ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager) {
        return currentWalkerManager.getVisibleUnoccupiedPositions();
    }
}
