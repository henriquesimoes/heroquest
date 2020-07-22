package br.unicamp.ic.mc322.heroquest.walker.skills;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;
import br.unicamp.ic.mc322.heroquest.walker.managers.player.Describable;

import java.util.ArrayList;

public abstract class Skill implements AbstractMapObjectVisitor, Describable, Comparable {
    protected Walker skillUser;
    protected ArrayList<MapObject> targets;
    protected String skillName;
    private String skillDescription;
    private WalkerManager walkerManager;
    private DisplayTargetsMode displayTargetsMode;

    public Skill(String skillName, String skillDescription, DisplayTargetsMode displayTargetsMode) {
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.displayTargetsMode = displayTargetsMode;
        this.targets = new ArrayList<>();
    }

    public void setWalkerManager(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
        this.skillUser = walkerManager.getWalker();
    }

    public abstract void useSkill(Walker summoner, MapObject targetObject);

    public String getName() {
        return skillName;
    }

    @Override
    public String getDescription() {
        return skillDescription;
    }

    public DisplayTargetsMode getDisplayTargetsMode() {
        return displayTargetsMode;
    }

    public abstract void updateTargets();

    public MapObject[] getTargets() {
        targets.clear();
        updateTargets();
        return targets.toArray(new MapObject[0]);
    }

    protected void accept(AbstractMapObjectVisitor visitor, Region region) {
        walkerManager.accept(visitor, region);
    }

    protected RegionSelector getUserRegionSelector() {
        return walkerManager.getRegionSelector();
    }

    @Override
    public void visit(StructuralObject structuralObject) {
    }

    @Override
    public void visit(FixedObject fixedObject) {
    }

    @Override
    public void visit(Walker walker) {
    }

    @Override
    public int compareTo(Object o) {
        Skill that = (Skill) o;
        int order = getName().compareTo(that.getName());
        return order != 0 ? order : Integer.compare(this.hashCode(), that.hashCode());
    }
}
