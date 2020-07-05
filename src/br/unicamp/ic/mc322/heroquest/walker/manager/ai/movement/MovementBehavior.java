package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.util.ArrayList;

public interface MovementBehavior {

    /**
     * @param possibleMoves
     * @return Chosen move indexed by 1, or 0 indicating no selection
     */
    int chooseMove(ArrayList<Coordinate> possibleMoves);
}
