package br.unicamp.ic.mc322.heroquest.skills.physicalSkill;

import br.unicamp.ic.mc322.heroquest.item.baseitems.DurableItem;
import br.unicamp.ic.mc322.heroquest.map.core.VisibleMap;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

import java.util.ArrayList;

public class AttackEnemy extends PhysicalSkill{

    public AttackEnemy(String skillName, DurableItem skilledItem, int attackDistance) {
        super(skillName, skilledItem, attackDistance);
    }

    @Override
    public void useSkill(VisibleMap visibleMap, Walker userWalker, MapObject target) {

    }

    @Override
    public ArrayList<MapObject> getTargets(Walker walkerReference, VisibleMap visibleMap) {
        return null;
    }
}
