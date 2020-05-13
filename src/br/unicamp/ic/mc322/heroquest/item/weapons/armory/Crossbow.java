package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.weapons.LongRangeWeapon;

public class Crossbow extends LongRangeWeapon {
    private static final String description = "Crossbow gives you a bonus of 3 combat dices." +
            " This is a long range weapon, you may fire at any monster in your sight. However," +
            " you can't attack a monster adjacent to you.";

    public Crossbow() {
        super("Longsword", description, 6, 350);
        super.setThrowDistance(10);
        super.setAttackBonus(3);
        super.setTwoHanded(true);
        super.setAttackDiagonally(true);
    }
}
