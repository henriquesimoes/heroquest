package br.unicamp.ic.mc322.heroquest.skills;

import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public abstract class Skill {
    private String skillName;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public abstract void useSkill(Walker summoner, MapObject targetObject);

    public String getSkillName() {
        return skillName;
    }

    public abstract ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager);

    protected ArrayList<MapObject> arrayListWalkerToMapObject(ArrayList<Walker> walkers){
        ArrayList<MapObject> mapObjects = new ArrayList<>();
        for(Walker walker : walkers)
            mapObjects.add(walker);
        return mapObjects;
    }
}
