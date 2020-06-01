package br.unicamp.ic.mc322.heroquest.map.core;

import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class VisibleMap {
    Map map;
    Walker walker;

    public void moveWalker(Coordinate coordinate) {
        map.moveObject(walker, coordinate);
    }

    public ArrayList<Coordinate> getCloseWalkablePositions(int maximumDistance) {
        return map.getCloseWalkablePositions(walker, maximumDistance);
    }

    public ArrayList<Walker> getAllWalkersWithinArea(Walker target, int radius) {
        return map.getAllWalkersWithinArea(target, radius);
    }

    public ArrayList<Walker> getEnemiesWithinArea(int radius) {
        ArrayList<Walker> targetWalkers = map.getAllWalkersWithinArea(walker, radius);
        return getListOfEnemies(targetWalkers);
    }

    public ArrayList<Walker> getFriendsWithinArea(int radius) {
        ArrayList<Walker> targetWalkers = map.getAllWalkersWithinArea(walker, radius);
        return getListOfFriends(targetWalkers);
    }

    public ArrayList<Walker> getEnemiesInAdjacentPositions() {
        ArrayList<Walker> targetWalkers = map.getEnemiesInAdjacentPositions(walker);
        return getListOfEnemies(targetWalkers);
    }

    public ArrayList<Walker> getEnemiesInTheFourDirections() {
        ArrayList<Walker> targetWalkers = map.getWalkersOfTheFourDirections(walker);
        return getListOfEnemies(targetWalkers);
    }

    private ArrayList<Walker> getListOfFriends(ArrayList<Walker> targetWalkers) {
        ArrayList<Walker> friends = new ArrayList<>();
        for (Walker targetWalker : targetWalkers) {
            if (walker.isFriend(targetWalker))
                friends.add(targetWalker);
        }
        return friends;
    }

    private ArrayList<Walker> getListOfEnemies(ArrayList<Walker> targetWalkers) {
        ArrayList<Walker> enemies = new ArrayList<>();
        for (Walker targetWalker : targetWalkers) {
            if (walker.isEnemy(targetWalker))
                enemies.add(targetWalker);
        }
        return enemies;
    }

}
