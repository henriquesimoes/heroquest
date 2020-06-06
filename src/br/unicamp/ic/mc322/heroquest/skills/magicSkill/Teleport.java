package br.unicamp.ic.mc322.heroquest.skills.magicSkill;

import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.skills.magicSkill.MagicSkill;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Teleport extends MagicSkill {
    public Teleport() {
        super("Teleporte", "O herói ou monstro se teletransporta para uma posição visível");
    }

    /**TODO: método apenas para teste, deve ser implementado no package do mapa*/
    public Coordinate showCanvasToPickPosition() {return new Coordinate(0, 0);}

    @Override
    public void useSkill(VisibleMap visibleMap, Walker userWalker, MapObject target) {

    }

    @Override
    public ArrayList<MapObject> getTargets(Walker walkerReference, VisibleMap visibleMap) {
        return visibleMap.getUnoccupiedPositionsVisible();
    }
}
