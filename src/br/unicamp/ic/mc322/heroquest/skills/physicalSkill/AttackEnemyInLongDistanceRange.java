package br.unicamp.ic.mc322.heroquest.skills.physicalSkill;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.map.core.MapObject;
import br.unicamp.ic.mc322.heroquest.map.geom.Region;
import br.unicamp.ic.mc322.heroquest.map.geom.RegionSelector;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.manager.WalkerManager;

import java.util.ArrayList;

public class AttackEnemyInLongDistanceRange extends PhysicalSkill {

    public AttackEnemyInLongDistanceRange(String skillName, Weapon skilledWeapon) {
        super(skillName, skilledWeapon);
    }

    @Override
    public ArrayList<MapObject> getTargets(WalkerManager currentWalkerManager) {
        RegionSelector regionSelector = currentWalkerManager.getRuler();
        Region region = regionSelector.getRoomRegion(false);
        ArrayList<Walker> enemies = currentWalkerManager.getEnemiesWithinArea(region);
        return currentWalkerManager.arrayListWalkerToMapObject(enemies);
    }
}
