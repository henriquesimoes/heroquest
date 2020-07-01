package br.unicamp.ic.mc322.heroquest.map.object.structural;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.MapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.PlacementStrategy;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Wall extends StructuralObject {
    public Wall(Coordinate position) {
        super(position);
    }

    @Override
    public boolean belongsToARoom() {
        return false;
    }

    @Override
    public boolean isAllowedToWalkOver() {
        return false;
    }

    @Override
    public void interact(Walker agent) {
        return;
    }

    @Override
    public String getRepresentationOnMenu() {
        return "Wall on " + getPosition();
    }
    
    @Override
    public boolean accept(PlacementStrategy strategy, MapObject object) {
        return strategy.canPlaceOn(this, object);
    }

    @Override
    public void accept(MapObjectVisitor visitor) {
        visitor.visit(this);
    }
}
