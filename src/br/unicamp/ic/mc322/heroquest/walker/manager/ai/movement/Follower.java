package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Follower extends MovementBehavior implements AbstractMapObjectVisitor {

    @Override
    public Coordinate chooseMove(ArrayList<Coordinate> possibleMoves) {
        Coordinate walkerPosition = walkerManager.getWalkerPosition();
        possibleMoves.add(walkerPosition); // insert "stay still" as a move
        Region region = walkerManager.getRegionSelector().getRoomRegion(false);
        walkerManager.accept(this, region);

        // if there are no visible enemies, make a random move
        if (enemies.size() == 0)
            return possibleMoves.get(Randomizer.nextInt(possibleMoves.size()));

        Coordinate choice = walkerManager.getCoordinateCloserToWalkers(possibleMoves, enemies);

        if (!possibleMoves.contains(choice))
            throw new IllegalStateException();

        return walkerPosition.equals(choice) ? null : choice;
    }

    @Override
    public void visit(Walker walker) {
        if (walker.isEnemy(walkerManager.getWalker()))
            enemies.add(walker);
    }

    @Override
    public void visit(StructuralObject structuralObject) {}

    @Override
    public void visit(FixedObject fixedObject) {}
}