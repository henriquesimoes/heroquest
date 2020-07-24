package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

/**
 * Visitor interface for accessing the map object general types.
 *
 * This should be used whenever a standard behavior is shared by
 * all object in the same group.
 */
public interface AbstractMapObjectVisitor {
    void visit(StructuralObject structuralObject);

    void visit(FixedObject fixedObject);

    void visit(Walker walker);
}
