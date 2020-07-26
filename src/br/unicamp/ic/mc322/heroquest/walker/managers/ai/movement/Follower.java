package br.unicamp.ic.mc322.heroquest.walker.managers.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.MovementBehavior;

import java.util.ArrayList;

public class Follower extends MovementBehavior implements AbstractMapObjectVisitor {
    @Override
    protected Coordinate chooseMove(ArrayList<Coordinate> possibleMoves) {
        Coordinate walkerPosition = walkerManager.getWalkerPosition();
        possibleMoves.add(walkerPosition); // insert "stay still" as a move
        Region region = walkerManager.getRegionSelector().getVisibleRegion();
        walkerManager.accept(this, region);

        // if there are no visible enemies, make a random move
        if (enemies.size() == 0)
            return possibleMoves.get(Randomizer.nextInt(possibleMoves.size()));

        Coordinate choice = walkerManager.getCoordinateCloserToWalkers(possibleMoves, enemies);

        if (!possibleMoves.contains(choice) && choice != null)
            throw new IllegalStateException();

        return walkerPosition.equals(choice) ? null : choice;
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(walkerManager.getWalker()))
            enemies.add(walker);
    }

    @Override
    public void visit(StructuralObject structuralObject) {
    }

    @Override
    public void visit(FixedObject fixedObject) {
    }
}
