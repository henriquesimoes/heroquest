package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public interface AbstractMapObjectVisitor {
    void visit(StructuralObject structuralObject);
    void visit(FixedObject fixedObject);
    void visit(Walker walker);
}
