package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.core.MapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.object.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.object.structural.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class Follower implements MovementBehavior, MapObjectVisitor {
    private ArrayList<Walker> enemies;
    private WalkerManager walkerManager;

    public Follower() {
        enemies = new ArrayList<>();
    }

    public void setWalkerManager(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
    }

    @Override
    public int chooseMove(ArrayList<Coordinate> possibleMoves) {
        Coordinate walkerPosition = walkerManager.getWalkerPosition();
        possibleMoves.add(0, walkerPosition); // insert "stay still" as the 0-th move

        Region region = walkerManager.getRegionSelector().getRoomRegion(false);

        walkerManager.accept(this, region);

        // if there is no visible enemies, then make a random move
        if (enemies.size() == 0) {
            RandomMovement randomMovement = new RandomMovement(walkerManager);
            return randomMovement.chooseMove(possibleMoves);
        }

        Coordinate choice = walkerManager.getCoordinateCloserToWalkers(possibleMoves, enemies);

        // Since a move has been inserted in the beginning, the return is indexed by 1 (just as desired)
        for (int i = 0; i < possibleMoves.size(); i++)
            if (possibleMoves.get(i).equals(choice))
                return i;

        throw new IllegalStateException();
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