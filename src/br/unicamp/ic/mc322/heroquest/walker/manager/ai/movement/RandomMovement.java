package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class RandomMovement extends MovementBehavior {
    private static RandomMovement instance;

    private RandomMovement(){}

    public static RandomMovement getInstance(){
        if (instance == null)
            instance = new RandomMovement();
        return instance;
    }

    @Override
    public int chooseMove(WalkerManager walkerManager, ArrayList<Coordinate> possibleMoves){
        return Randomizer.randInt(1, possibleMoves.size()); // choose a move indexed by 1
    }
}
