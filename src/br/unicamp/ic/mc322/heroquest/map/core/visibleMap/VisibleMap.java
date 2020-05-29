package br.unicamp.ic.mc322.heroquest.map.core.visibleMap;

import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import org.omg.CORBA.ARG_IN;

import java.util.ArrayList;

public class VisibleMap {
    Map map;
    Walker walker;

    public void moveWalker(Coordinate coordinate) {
        map.moveObject(walker, coordinate);
    }

    public ArrayList<Coordinate> getPositionsWithDistanceUp(int limitPositionInMove) {
        return map.getPositionsWithDistanceUp(limitPositionInMove);
    }

    public ArrayList<Walker> getAllWalkersInRadiusOf(Walker target, int radius){
        return map.getAllWalkersInRadiusOf(target, radius);
    }

    public ArrayList<Walker> getEnemiesInRadiusOf(int radius){
        ArrayList<Walker> targetWalkers = map.getAllWalkersInRadiusOf(walker, radius);
        return getListOfEnemies(targetWalkers);
    }

    public ArrayList<Walker> getFriendsInRadiusOf(int radius){
        ArrayList<Walker> targetWalkers = map.getAllWalkersInRadiusOf(walker, radius);
        return getListOfFriends(targetWalkers);
    }

    public ArrayList<Walker> getEnemiesOfTheAdjacentPositions(){
        ArrayList<Walker> targetWalkers = map.getWalkersOfTheAdjacentPositions();
        return getListOfEnemies(targetWalkers);
    }

    public ArrayList<Walker> getEnemiesOfTheFourDirections(){
        ArrayList<Walker> targetWalkers = map.getWalkersOfTheFourDirections();
        return getListOfEnemies(targetWalkers);
    }

    private ArrayList<Walker> getListOfFriends(ArrayList<Walker> targetWalkers){
        ArrayList<Walker> friends = new ArrayList<>();
        for(Walker targetWalker : targetWalkers){
            if (walker.isFriend(targetWalker))
                friends.add(targetWalker);
        }
        return friends;
    }

    private ArrayList<Walker> getListOfEnemies(ArrayList<Walker> targetWalkers){
        ArrayList<Walker> enemies = new ArrayList<>();
        for(Walker targetWalker : targetWalkers){
            if (walker.isEnemy(targetWalker))
                enemies.add(targetWalker);
        }
        return enemies;
    }

}
