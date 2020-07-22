package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.MoveAction;

import java.util.ArrayList;

public class MoveAIAction extends MoveAction {
    private final WalkerAI walkerAI;

    MoveAIAction(WalkerAI walkerAI) {
        super(walkerAI);
        this.walkerAI = walkerAI;
    }

    @Override
    public boolean execute() {
        Walker walker = walkerAI.getWalker();
        int limitPositionInMove = walker.getPositionLimitInMovement();
        Region region = walkerAI.getRegionSelector().getLimitedRegion(limitPositionInMove, true);

        ArrayList<Coordinate> possibleMoves = region.toArrayList();

        Coordinate chosenMove = chooseMove(possibleMoves);
        if (chosenMove != null)
            walkerAI.moveWalker(chosenMove);

        return true;
    }

    public Coordinate chooseMove(ArrayList<Coordinate> possibleMoves) {
        return walkerAI.getMovementBehavior().chooseMove(possibleMoves);
    }
}
