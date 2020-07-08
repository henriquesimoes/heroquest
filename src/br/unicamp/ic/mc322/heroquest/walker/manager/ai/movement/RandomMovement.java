package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

import java.util.ArrayList;

public class RandomMovement extends MovementBehavior {
    @Override
    public Coordinate chooseMove(ArrayList<Coordinate> possibleMoves){
        possibleMoves.add(walkerManager.getWalkerPosition());
        return possibleMoves.get(Randomizer.nextInt(possibleMoves.size()));
    }
}
