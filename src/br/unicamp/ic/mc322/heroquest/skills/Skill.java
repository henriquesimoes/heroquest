package br.unicamp.ic.mc322.heroquest.skills;

import br.unicamp.ic.mc322.heroquest.map.core.AbstractMapObjectVisitor;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.map.objects.FixedObject;
import br.unicamp.ic.mc322.heroquest.map.objects.StructuralObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

import java.util.ArrayList;

public abstract class Skill implements AbstractMapObjectVisitor {
    private WalkerManager walkerManager;
    protected String skillName;
    protected Walker skillUser;
    protected ArrayList<MapObject> targets;

    public Skill(String skillName) {
        this.skillName = skillName;
        this.targets = new ArrayList<>();
    }

    public void setWalkerManager(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
        this.skillUser = walkerManager.getWalker();
    }

    public abstract void useSkill(Walker summoner, MapObject targetObject);

    public String getSkillName() {
        return skillName;
    }

    public abstract void updateTargets();

    public ArrayList<MapObject> getTargets() {
        targets.clear();
        updateTargets();
        return targets;
    }

    protected void accept(AbstractMapObjectVisitor visitor, Region region) {
        walkerManager.accept(visitor, region);
    }

    protected RegionSelector getUserRegionSelector() {
        return walkerManager.getRegionSelector();
    }

    @Override
    public void visit(StructuralObject structuralObject) {}

    @Override
    public void visit(FixedObject fixedObject) {}

    @Override
    public void visit(Walker walker) {}
}
