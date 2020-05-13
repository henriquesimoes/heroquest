package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.weapons.LongRangeWeapon;

public class Dagger extends LongRangeWeapon {
    private static final String description = "Dagger gives you a bonus of 1 combat dices." +
            " Daggers allow you to thrown at any enemies in your line of sight. You can thrown it" +
            "diagonally, but you can't not attack diagonally while stabbing a monster";

    public Dagger() {
        super("Longsword", description, 6, 25);
        setAttackDistance(1);
        super.setThrowDistance(3);
        super.setAttackBonus(1);
        super.setTwoHanded(true);
        super.setAttackDiagonally(true);
    }
}
