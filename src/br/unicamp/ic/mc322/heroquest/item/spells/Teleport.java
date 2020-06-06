package br.unicamp.ic.mc322.heroquest.item.spells;

import br.unicamp.ic.mc322.heroquest.skills.MagicSkill;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Teleport extends MagicSkill {
    public Teleport() {
        super("Teleporte", "O herói ou monstro se teletransporta para uma posição visível");
    }

    @Override
    public void useSkill(VisibleMap visibleMap, Walker summoner, Walker targetWalker) {
        ArrayList<Coordinate> positionsInSight = summoner.getPositionsInEntitySight();
        summoner.setPosition(showCanvasToPickPosition());
    }

    /**TODO: método apenas para teste, deve ser implementado no package do mapa*/
    public Coordinate showCanvasToPickPosition() {return new Coordinate(0, 0);}
}
