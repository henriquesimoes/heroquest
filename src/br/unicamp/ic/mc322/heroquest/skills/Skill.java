package br.unicamp.ic.mc322.heroquest.skills;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public abstract class Skill {
    private String skillName;
    private int skillIntensity;
    private CollectableItem skilledItem;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public Skill(String skillName, CollectableItem skilledItem) {
        this.skillName = skillName;
        this.skilledItem = skilledItem;
    }

    public CollectableItem getSkilledItem() {
        return skilledItem;
    }

    public abstract void useSkill(VisibleMap visibleMap, Walker userWalker, MapObject target);

    public String getSkillName() {
        return skillName;
    }

    public void setSkillIntensity(int intensity) {
        this.skillIntensity = intensity;
    }

    public int getSkillIntensity() {
        return skillIntensity;
    }

    /**TODO:
     * Tudo, porque ela não faz nada kkkk */
    public abstract ArrayList<MapObject> getTargets(Walker walkerReference, VisibleMap visibleMap);

    protected ArrayList<Walker> getFriendsWithinArea(Walker currentWalker, VisibleMap visibleMap, Distance distance) {
        ArrayList<Walker> targetWalkers = visibleMap.getAllWalkersWithinArea(currentWalker, distance);
        return getListOfFriends(currentWalker, targetWalkers);
    }

    protected ArrayList<Walker> getEnemiesWithinArea(Walker currentWalker, VisibleMap visibleMap, Distance distance) {
        ArrayList<Walker> targetWalkers = visibleMap.getAllWalkersWithinArea(currentWalker, distance);
        return getListOfEnemies(currentWalker, targetWalkers);
    }

    private ArrayList<Walker> getListOfFriends(Walker currentWalker, ArrayList<Walker> targetWalkers) {
        ArrayList<Walker> friends = new ArrayList<>();
        for (Walker targetWalker : targetWalkers)
            if (currentWalker.isFriend(targetWalker))
                friends.add(targetWalker);
        return friends;
    }

    private ArrayList<Walker> getListOfEnemies(Walker currentWalker, ArrayList<Walker> targetWalkers) {
        ArrayList<Walker> enemies = new ArrayList<>();
        for (Walker targetWalker : targetWalkers)
            if (currentWalker.isEnemy(targetWalker))
                enemies.add(targetWalker);
        return enemies;
    }

    protected ArrayList<MapObject> arrayListWalkerToMapObject(ArrayList<Walker> walkers){
        ArrayList<MapObject> mapObjects = new ArrayList<>();
        for(Walker walker : walkers)
            mapObjects.add(walker);
        return mapObjects;
    }

}
