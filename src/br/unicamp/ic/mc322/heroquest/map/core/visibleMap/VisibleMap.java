package br.unicamp.ic.mc322.heroquest.map.core.visibleMap;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class VisibleMap {
    Map map;
    Walker walker;

    public void moveWalker(Coordinate coordinate) {
        map.moveObject(walker, coordinate);
    }

    public ArrayList<Coordinate> getPositionsWithDistanceUp(int limitPositionInMove) {
        return map.getPositionsWithDistanceUp(limitPositionInMove);
    }
}
