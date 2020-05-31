package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.AttackEnemy;
import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.AttackEnemyInLongDistanceRange;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;

public class Staff extends Weapon {
    private static final String description = "Staff gives you a bonus of 1 combat dice." +
            " You can attack your enemies in a long distance range and two handed weapon.";

    public Staff() {
        super("Staff", description, 3, 100);
        setAttackDistance(4);
        setAttackBonus(1);
        setTwoHanded(true);
        setAttackDiagonally(true);
        setNewSkill(new AttackEnemy("Golpear", this, 4));
    }
}
