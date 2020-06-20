package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public abstract class MovementBehavior {

    /**
     * @param walkerManager
     * @param possibleMoves
     * @return Chosen move indexed by 1, or 0 indicating no selection
     */
    public abstract int chooseMove(WalkerManager walkerManager, ArrayList<Coordinate> possibleMoves);
}
