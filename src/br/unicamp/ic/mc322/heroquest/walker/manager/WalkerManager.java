package br.unicamp.ic.mc322.heroquest.walker.manager;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.geom.Ruler;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public abstract class WalkerManager {
    protected Walker walker;
    protected VisibleMap visibleMap;

    public WalkerManager(Walker walker) {
        this.walker = walker;
    }

    public abstract void playTurn();

    /**
     *
     * @param items
     * @return Chosen item indexed by 1, or 0 indicating no selection
     */
    protected abstract int chooseItem(ArrayList<CollectableItem> items);

    /**
     *
     * @param possibleMoves
     * @return Chosen move indexed by 1, or 0 indicating no selection
     */
    protected abstract int chooseMove(ArrayList<Coordinate> possibleMoves);

    /**
     * @param skills
     * @return Chosen skill indexed by 1, or 0 indicating no selection
     */
    protected abstract int chooseSkill(ArrayList<Skill> skills);

    /**
     * @param targets
     * @return Chosen target indexed by 1, or 0 indicating no selection
     */
    protected abstract int chooseTargetSkill(ArrayList<MapObject> targets);

    public ArrayList<Walker> getFriendsWithinArea(Distance distance) {
        ArrayList<Walker> targetWalkers = visibleMap.getAllWalkersWithinArea(distance);
        return getListOfFriends(targetWalkers);
    }

    public ArrayList<Walker> getEnemiesWithinArea(Distance distance) {
        ArrayList<Walker> targetWalkers = visibleMap.getAllWalkersWithinArea(distance);
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

    public ArrayList<MapObject> getVisibleUnoccupiedPositions() {
        return visibleMap.getVisibleUnoccupiedPositions();
    }

    public ArrayList<Walker> getVisibleEnemies(){
        ArrayList<Walker> walkers = visibleMap.getVisibleWalkers();
        return getListOfEnemies(walkers);
    }

    public void moveWalker(Coordinate position) {
        visibleMap.moveWalker(position);
    }

    public Ruler getRuler(){
        return visibleMap.getRuler();
    }

    protected void useItems() {
        ArrayList<CollectableItem> items = walker.getItems();
        int choice = chooseItem(items);
        if (choice != 0) {
            CollectableItem choosedItem = items.get(choice - 1);
            choosedItem.useItem(walker);
        }
    }

    protected boolean makeMove() {
        int limitPositionInMove = walker.getPositionLimitInMovement();
        ArrayList<Coordinate> possibleMoves = visibleMap.getCloseWalkablePositions(limitPositionInMove);

        int choice = chooseMove(possibleMoves);
        if (choice != 0)
            visibleMap.moveWalker(possibleMoves.get(choice - 1));

        return true;
    }

    protected boolean useSkill() {
        int choice;
        ArrayList<Skill> skills = walker.getSkills();

        choice = chooseSkill(skills);

        if (choice == 0)
            return false;

        Skill chosenSkill = skills.get(choice - 1);
        ArrayList<MapObject> targets = chosenSkill.getTargets(this);

        choice = chooseTargetSkill(targets);

        if (choice == 0)
            return false;

        MapObject target = targets.get(choice - 1);
        chosenSkill.useSkill(walker, target);

        return true;
    }

    public ArrayList<MapObject> arrayListWalkerToMapObject(ArrayList<Walker> walkers){
        ArrayList<MapObject> mapObjects = new ArrayList<>();
        for(Walker walker : walkers)
            mapObjects.add(walker);
        return mapObjects;
    }

    public Coordinate getCoordinateCloserToWalkers(ArrayList<Coordinate> coordinates, ArrayList<Walker> walkers) {
        ArrayList<MapObject> objects = new ArrayList<>();
        for(Walker walker : walkers){
            objects.add(walker);
        }
        return visibleMap.getCoordinateCloserToObject(coordinates, objects);
    }

    public Coordinate getPositionWalker() {
        return walker.getPosition();
    }
}
