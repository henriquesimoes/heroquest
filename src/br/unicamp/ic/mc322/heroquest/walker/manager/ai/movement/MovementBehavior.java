package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.manager.ai.Behavior;

import java.util.ArrayList;

public abstract class MovementBehavior extends Behavior {
    public abstract Coordinate chooseMove(ArrayList<Coordinate> possibleMoves);
}
