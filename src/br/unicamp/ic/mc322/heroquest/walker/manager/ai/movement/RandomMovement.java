package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class RandomMovement implements MovementBehavior {
    private static RandomMovement instance;
    private WalkerManager walkerManager;

    public RandomMovement() {}

    public RandomMovement(WalkerManager walkerManager) {
        setWalkerManager(walkerManager);
    }

    public void setWalkerManager(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
    }

    @Override
    public int chooseMove(ArrayList<Coordinate> possibleMoves){
        return Randomizer.randInt(1, possibleMoves.size()); // choose a move indexed by 1
    }
}
