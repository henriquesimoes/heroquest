package br.unicamp.ic.mc322.heroquest.walker.manager;

import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public abstract class WalkerManager {
    protected Walker walker;
    protected VisibleMap visibleMap;

    public WalkerManager(Walker walker) {
        this.walker = walker;
    }

    public abstract void playTurn();

    public ArrayList<Walker> getFriendsWithinArea(Distance distance) {
        ArrayList<Walker> targetWalkers = visibleMap.getAllWalkersWithinArea(walker, distance);
        return getListOfFriends(targetWalkers);
    }

    public ArrayList<Walker> getEnemiesWithinArea(Distance distance) {
        ArrayList<Walker> targetWalkers = visibleMap.getAllWalkersWithinArea(walker, distance);
        return getListOfEnemies(targetWalkers);
    }

    public ArrayList<Walker> getListOfFriends(ArrayList<Walker> targetWalkers) {
        ArrayList<Walker> friends = new ArrayList<>();
        for (Walker targetWalker : targetWalkers)
            if (walker.isFriend(targetWalker))
                friends.add(targetWalker);
        return friends;
    }

    public ArrayList<Walker> getListOfEnemies(ArrayList<Walker> targetWalkers) {
        ArrayList<Walker> enemies = new ArrayList<>();
        for (Walker targetWalker : targetWalkers)
            if (walker.isEnemy(targetWalker))
                enemies.add(targetWalker);
        return enemies;
    }

    public ArrayList<MapObject> getUnoccupiedPositionsVisible() {
        return visibleMap.getUnoccupiedPositionsVisible();
    }

    public void moveWalker(Coordinate position) {
        visibleMap.moveWalker(position);
    }
}
