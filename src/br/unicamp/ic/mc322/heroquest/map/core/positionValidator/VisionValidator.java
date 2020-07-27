package br.unicamp.ic.mc322.heroquest.map.core.positionValidator;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.PositionValidator;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class VisionValidator implements PositionValidator, AbstractMapObjectVisitor {
    private final Map map;
    private boolean lastVisitedIsValid;
    private boolean lastVisitedIsExpandable;

    public VisionValidator(Map map) {
        this.map = map;
    }

    @Override
    public void visit(StructuralObject structuralObject) {
        lastVisitedIsValid = true;
        lastVisitedIsExpandable = structuralObject.isAllowedToWalkOver();
    }

    @Override
    public void visit(FixedObject fixedObject) {
        lastVisitedIsValid = lastVisitedIsExpandable = true;
    }

    @Override
    public void visit(Walker walker) {
        lastVisitedIsValid = true;
        lastVisitedIsExpandable = false;
    }

    @Override
    public boolean isValid(Coordinate coordinate) {
        map.accept(this, coordinate);
        return lastVisitedIsValid;
    }

    @Override
    public boolean isExpandable(Coordinate coordinate) {
        map.accept(this, coordinate);
        return lastVisitedIsExpandable;
    }
}
