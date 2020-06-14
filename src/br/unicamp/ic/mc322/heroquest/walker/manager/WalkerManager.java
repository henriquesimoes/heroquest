package br.unicamp.ic.mc322.heroquest.walker.manager;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public abstract class WalkerManager {
    protected Walker walker;
    private Map map;
    private RegionSelector regionSelector;

    public WalkerManager(Map map) {
        this.map = map;
        this.regionSelector = map.getRegionSelector();
    }

    public void setWalker(Walker walker){
        this.walker = walker;
        regionSelector.useAsReference(walker.getPosition());
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

    public ArrayList<Walker> getFriendsWithinArea(Region region) {
        ArrayList<Walker> targetWalkers = map.getAllWalkersWithinArea(region);
        return getListOfFriends(targetWalkers);
    }

    public ArrayList<Walker> getEnemiesWithinArea(Region region) {
        ArrayList<Walker> targetWalkers = map.getAllWalkersWithinArea(region);
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
        return map.getUnoccupiedPositions(walker);
    }

    public ArrayList<Walker> getVisibleEnemies(){
        ArrayList<Walker> walkers = map.getAllWalkersWithinArea(regionSelector.getRoomRegion(false));
        return getListOfEnemies(walkers);
    }

    public void moveWalker(Coordinate position) {
        map.moveObject(walker, position);
    }

    public RegionSelector getRuler(){
        return map.getRegionSelector();
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
        Region region = regionSelector.getLimitedRegion(limitPositionInMove, true);
        ArrayList<Coordinate> possibleMoves = map.getWalkablePositions(region);

        int choice = chooseMove(possibleMoves);
        if (choice != 0)
            map.moveObject(walker, possibleMoves.get(choice - 1));

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
        return map.getCoordinateCloserToObject(coordinates, objects);
    }

    public Coordinate getPositionWalker() {
        return walker.getPosition();
    }

    public boolean isAlive() {
        return walker.isAlive();
    }

    public abstract void showMessage(String message);
}
