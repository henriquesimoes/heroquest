package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.object.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.object.structural.Wall;

public interface PlacementStrategy {
    boolean canPlaceOn(Floor floor, MapObject object);
    boolean canPlaceOn(Wall wall, MapObject object);
    boolean canPlaceOn(Door door, MapObject object);
}
