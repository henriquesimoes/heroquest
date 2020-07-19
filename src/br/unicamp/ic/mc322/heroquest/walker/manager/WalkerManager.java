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
    private final RegionSelector regionSelector;

    public WalkerManager(Map map) {
        this.map = map;
        this.regionSelector = map.getRegionSelector();
    }

    public void setWalker(Walker walker){
        this.walker = walker;
        regionSelector.useAsReference(walker);
    }

    public abstract void playTurn();

    protected abstract CollectableItem chooseItem(ArrayList<CollectableItem> items);

    protected abstract Coordinate chooseMove(ArrayList<Coordinate> possibleMoves);

    protected abstract Skill chooseSkill(ArrayList<Skill> skills);

    protected abstract MapObject chooseTargetSkill(ArrayList<MapObject> targets);

    public void moveWalker(Coordinate position) {
        map.move(walker, position);
    }

    public RegionSelector getRegionSelector(){
        return regionSelector;
    }

    protected void useItems() {
        ArrayList<CollectableItem> items = walker.getItems();
        CollectableItem chosenItem = chooseItem(items);
        if (chosenItem != null)
            chosenItem.useItem(walker);
    }

    protected boolean makeMove() {
        int limitPositionInMove = walker.getPositionLimitInMovement();
        Region region = regionSelector.getLimitedRegion(limitPositionInMove, true);

        ArrayList<Coordinate> possibleMoves = region.toArrayList();

        Coordinate chosenMove = chooseMove(possibleMoves);
        if (chosenMove != null)
            moveWalker(chosenMove);

        return true;
    }

    protected boolean useSkill() {
        ArrayList<Skill> skills = walker.getSkills();
        Skill chosenSkill = chooseSkill(skills);

        if (chosenSkill == null)
            return false;

        ArrayList<MapObject> targets = chosenSkill.getTargets();
        MapObject target = chooseTargetSkill(targets);

        if (target == null)
            return false;

        chosenSkill.useSkill(walker, target);

        return true;
    }

    public Coordinate getCoordinateCloserToWalkers(ArrayList<Coordinate> coordinates, ArrayList<Walker> walkers) {
        ArrayList<MapObject> objects = new ArrayList<>();
        for(Walker walker : walkers)
            objects.add(walker);

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
