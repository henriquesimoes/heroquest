package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;

import java.util.ArrayList;

public abstract class MovementBehavior extends Behavior {
    protected abstract Coordinate chooseMove(ArrayList<Coordinate> possibleMoves);
}
