package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.skills.weaponskills.AttackEnemy;

public class Staff extends Weapon {
    private static final String DESCRIPTION = "Staff gives you a bonus of 1 combat dice." +
            " You can attack your enemies in a long distance range and two handed weapon.";

    public Staff() {
        super("Staff", DESCRIPTION, 3, 100);
        setAttackDistance(4);
        setAttackBonus(1);
        setTwoHanded(true);
        setAttackDiagonally(true);
        setNewSkill(new AttackEnemy("Golpear", this, 4));
    }
}
