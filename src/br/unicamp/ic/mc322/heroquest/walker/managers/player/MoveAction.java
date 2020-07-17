package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Direction;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.util.playerInterface.PlayerInterface;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.WalkerPlayer;

import java.util.ArrayList;

public class MoveAction implements Action {
    private WalkerPlayer walkerPlayer;

    public MoveAction(WalkerPlayer walkerPlayer) {
        this.walkerPlayer = walkerPlayer;
    }

    @Override
    public String getDescription() {
        return "Execute movement";
    }

    @Override
    public boolean execute() {
        Walker walker = walkerPlayer.getWalker();
        PlayerInterface ioInterface = walkerPlayer.getPlayerInterface();
        int limitPositionInMove = walker.getPositionLimitInMovement();

        for (int i = limitPositionInMove; i > 0; ) {
            ioInterface.showMessage(String.format("Remaining movements: %d", i));
            Direction direction = ioInterface.getMoveDirection();

            if (direction == null)
                break;

            Coordinate chosenMove = walker.getPosition().shift(direction);
            Region region = walkerPlayer.getRegionSelector().getCardinalRegion(true);
            ArrayList<Coordinate> possibleMoves = region.toArrayList();

            if (possibleMoves.contains(chosenMove)) {
                walkerPlayer.moveWalker(chosenMove);
                i--;
            } else
                ioInterface.showMessage("Invalid movement");
            walkerPlayer.updateScreen();
        }
        return true;
    }
}
