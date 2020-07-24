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
    private final String skillDescription;
    private WalkerManager walkerManager;
    private final DisplayTargetsMode displayTargetsMode;

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

    /**
     * Defines how a skill is used by the `skillUser` and how it impacts
     * the chosen target.
     *
     * @param targetObject - skill target.
     */
    public abstract void useSkill(MapObject targetObject);

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


    public MapObject[] getTargets() {
        targets.clear();
        updateTargets();
        return targets.toArray(new MapObject[0]);
    }

    /**
     * Updates the possible targets to be chosen by the user.
     */
    protected abstract void updateTargets();

    /**
     * Propagates the message that the concrete skill will use the given
     * region to find its targets in the map.
     *
     * The region objects are then accessible by the `visit` methods of
     * the `AbstractMapObjectVisitor` interface.
     *
     * @param region - map region to be visited
     */
    protected void use(Region region) {
        walkerManager.accept(this, region);
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
