package br.unicamp.ic.mc322.heroquest.walker.manager;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.core.MapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.skills.Skill;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public abstract class WalkerManager {
    protected Walker walker;
    protected Map map;
    private RegionSelector regionSelector;

    public WalkerManager(Map map) {
        this.map = map;
        this.regionSelector = map.getRegionSelector();
    }

    public void setWalker(Walker walker){
        this.walker = walker;
        regionSelector.useAsReference(walker);
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

    public void moveWalker(Coordinate position) {
        map.move(walker, position);
    }

    public RegionSelector getRegionSelector(){
        return regionSelector;
    }

    protected void useItems() {
        ArrayList<CollectableItem> items = walker.getItems();
        int choice = chooseItem(items);
        if (choice != 0) {
            CollectableItem chosenItem = items.get(choice - 1);
            chosenItem.useItem(walker);
        }
    }

    protected boolean makeMove() {
        int limitPositionInMove = walker.getPositionLimitInMovement();
        Region region = regionSelector.getLimitedRegion(limitPositionInMove, true);

        // TODO: include toArrayList method on Region
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        for (Coordinate coordinate : region)
            possibleMoves.add(coordinate);

        int choice = chooseMove(possibleMoves);
        if (choice != 0)
            moveWalker(possibleMoves.get(choice - 1));

        return true;
    }

    protected boolean useSkill() {
        int choice;
        ArrayList<Skill> skills = walker.getSkills();

        choice = chooseSkill(skills);

        if (choice == 0)
            return false;

        Skill chosenSkill = skills.get(choice - 1);
        ArrayList<MapObject> targets = chosenSkill.getTargets();

        choice = chooseTargetSkill(targets);

        if (choice == 0)
            return false;

        MapObject target = targets.get(choice - 1);
        chosenSkill.useSkill(walker, target);

        return true;
    }

    public Coordinate getCoordinateCloserToWalkers(ArrayList<Coordinate> coordinates, ArrayList<Walker> walkers) {
        ArrayList<MapObject> objects = new ArrayList<>();
        for(Walker walker : walkers){
            objects.add(walker);
        }
        return map.getCoordinateCloserToObject(coordinates, objects);
    }

    public Coordinate getWalkerPosition() {
        return walker.getPosition();
    }

    public Walker getWalker(){
        return walker;
    }

    public boolean isAlive() {
        return walker.isAlive();
    }

    public abstract void showMessage(String message);

    public String getWalkerName() {
        return walker.getName();
    }

    public void accept(MapObjectVisitor visitor, Region region) {
        map.accept(visitor, region);
    }
}
