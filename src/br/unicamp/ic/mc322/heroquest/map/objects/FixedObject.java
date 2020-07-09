package br.unicamp.ic.mc322.heroquest.map.objects;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.MapUnit;
import br.unicamp.ic.mc322.heroquest.map.placement.PlacementStrategy;

public abstract class FixedObject extends MapObject {

    @Override
    public boolean accept(PlacementStrategy strategy, MapObject object) {
        return false;
    }

    @Override
    public void goTo(MapUnit unit) {
        unit.add(this);
    }

    @Override
    public void accept(AbstractMapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
