package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.Weapon;

public class Staff extends Weapon {
    private static final String description = "Staff gives you a bonus of 1 combat dice." +
            " You can attack your enemies in a long distance range and two handed weapon.";

    public Staff() {
        super("Staff", description, 3, 100);
        super.setAttackDistance(4);
        super.setAttackBonus(1);
        super.setTwoHanded(true);
        super.setAttackDiagonally(true);
    }
}
