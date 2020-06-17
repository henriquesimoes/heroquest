package br.unicamp.ic.mc322.heroquest.walker.manager.ai.movement;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class Follower extends MovementBehavior {
    private static Follower instance;

    public Follower() {}

    public static Follower getInstance(){
        if (instance == null)
            instance = new Follower();
        return instance;
    }

    @Override
    public int chooseMove(WalkerManager walkerManager, ArrayList<Coordinate> possibleMoves){
        ArrayList<Walker> enemies = walkerManager.getVisibleEnemies();
        Coordinate walkerPosition = walkerManager.getWalkerPosition();
        possibleMoves.add(0, walkerPosition); // insert "stay still" as the 0-th move

        // if there is no visible enemies, then make a random move
        if (enemies.size() == 0) {
            RandomMovement randomMovement = RandomMovement.getInstance();
            return randomMovement.chooseMove(walkerManager, possibleMoves);
        }

        Coordinate choice = walkerManager.getCoordinateCloserToWalkers(possibleMoves, enemies);

        // Since a move has been inserted in the beginning, the return is indexed by 1 (just as desired)
        for (int i = 0; i < possibleMoves.size(); i++)
            if (possibleMoves.get(i).equals(choice))
                return i;

        throw new IllegalStateException();
    }
}
