package br.unicamp.ic.mc322.heroquest.walker.managers.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.managers.ai.MovementBehavior;

import java.util.ArrayList;

public class RandomMovement extends MovementBehavior {
    @Override
    protected Coordinate chooseMove(ArrayList<Coordinate> possibleMoves) {
        possibleMoves.add(walkerManager.getWalkerPosition());
        return possibleMoves.get(Randomizer.nextInt(possibleMoves.size()));
    }
}
