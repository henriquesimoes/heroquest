package br.unicamp.ic.mc322.heroquest.skills;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;
import br.unicamp.ic.mc322.heroquest.map.geom.Coordinate;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.util.pair.Pair;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class Skill {
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

    public void useSkill(VisibleMap visibleMap, Walker userWalker, Walker targetWalker) {}

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
    public ArrayList<Pair<Walker, Coordinate>> getTargets(VisibleMap visibleMap) {
        return null;
    }

}
