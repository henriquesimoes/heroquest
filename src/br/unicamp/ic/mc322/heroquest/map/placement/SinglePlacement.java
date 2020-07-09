package br.unicamp.ic.mc322.heroquest.map.placement;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Door;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Floor;
import br.unicamp.ic.mc322.heroquest.map.objects.structural.Wall;

public class SinglePlacement implements PlacementStrategy {
    @Override
    public boolean canPlaceOn(Floor floor, MapObject object) {
        return true;
    }

    @Override
    public boolean canPlaceOn(Wall wall, MapObject object) {
        return false;
    }

    @Override
    public boolean canPlaceOn(Door door, MapObject object) {
        return false;
    }
}
