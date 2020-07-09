package br.unicamp.ic.mc322.heroquest.map.placement;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;

public interface PlacementStrategy {
    boolean canPlaceOn(Floor floor, MapObject object);
    boolean canPlaceOn(Wall wall, MapObject object);
    boolean canPlaceOn(Door door, MapObject object);
}
