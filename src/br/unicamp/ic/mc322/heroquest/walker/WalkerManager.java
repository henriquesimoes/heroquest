package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.Map;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.skills.Skill;

import java.util.ArrayList;

public abstract class WalkerManager {
    protected Walker walker;
    protected Map map;
    protected RegionSelector regionSelector;

    public void moveWalker(Coordinate position) {
        map.move(walker, position);
    }

    public RegionSelector getRegionSelector() {
        return regionSelector;
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

    public Walker getWalker() {
        return walker;
    }

    public boolean isAlive() {
        return walker.isAlive();
    }

    public String getWalkerName() {
        return walker.getName();
    }

    public void accept(AbstractMapObjectVisitor visitor, Region region) {
        map.accept(visitor, region);
    }

    protected String getStatus() {
        return walker.getStatus();
    }

    protected void useItems() {
        ArrayList<CollectableItem> items = walker.getItems();
        CollectableItem chosenItem = chooseItem(items);
        if (chosenItem != null)
            chosenItem.useItem(walker);
    }

    protected abstract boolean makeMove();

    protected boolean useSkill() {
        ArrayList<Skill> skills = walker.getSkills();
        Skill chosenSkill = chooseSkill(skills);

        if (chosenSkill == null)
            return false;

        ArrayList<MapObject> targets = chosenSkill.getTargets();
        MapObject target = chooseTarget(targets);

        if (target == null)
            return false;

        chosenSkill.useSkill(walker, target);

        return true;
    }

    protected void setMap(Map map){
        changeMap(map);
    }

    protected void changeMap(Map map){
        this.map = map;
        this.regionSelector = map.getRegionSelector();
        regionSelector.useAsReference(walker);
    }

    void setWalker(Walker walker){
        this.walker = walker;
    }

    public abstract void playTurn();
    public abstract void showMessage(String message);
    protected abstract CollectableItem chooseItem(ArrayList<CollectableItem> items);
    protected abstract Skill chooseSkill(ArrayList<Skill> skills);
    protected abstract MapObject chooseTarget(ArrayList<MapObject> targets);
}
