package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class Follower extends MovementBehavior {
    private static Follower instance;

    public Follower(){}

    public static Follower getInstance(){
        if (instance == null)
            instance = new Follower();
        return instance;
    }

    @Override
    public int chooseMove(WalkerManager walkerManager, ArrayList<Coordinate> possibleMoves){
        ArrayList<Walker> enemies = walkerManager.getVisibleEnemies();
        Coordinate positionWalker = walkerManager.getPositionWalker();
        possibleMoves.add(0, positionWalker); // insert stay still as the move 0

        // if not has enemies visible, then make a random move
        if (enemies.size() == 0){
            RandomMovement randomMovement = RandomMovement.getInstance();
            return randomMovement.chooseMove(walkerManager, possibleMoves);
        }

        Coordinate choice = walkerManager.getCoordinateCloserToWalkers(possibleMoves, enemies);

        // how was insert a move in begin, the return is indexed by 1 (just as desired)
        for (int i = 0; i < possibleMoves.size(); i++){
            if (possibleMoves.get(i).equals(choice))
                return i;
        }

        new IllegalStateException();
        return 0;
    }
}
