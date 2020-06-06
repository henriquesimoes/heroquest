package br.unicamp.ic.mc322.heroquest.skills.physicalSkill;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.geom.Distance;
import br.unicamp.ic.mc322.heroquest.map.object.MapObject;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class AttackEnemyInLongDistanceRange extends PhysicalSkill {

    public AttackEnemyInLongDistanceRange(String skillName, Weapon skilledWeapon) {
        super(skillName, skilledWeapon);
    }

    @Override
    public ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager) {
        Distance distance = null;
        // TODO: configure distance
        ArrayList<Walker> enemies = currentWalkerManager.getEnemiesWithinArea(distance);
        return arrayListWalkerToMapObject(enemies);
    }
}
