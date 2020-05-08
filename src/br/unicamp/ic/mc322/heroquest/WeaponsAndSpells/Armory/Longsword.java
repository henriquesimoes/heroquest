package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells.Armory;

import br.unicamp.ic.mc322.heroquest.WeaponsAndSpells.Weapon;

public class Longsword extends Weapon {
    private static final String description = "Longsword gives you a bonus of 3 combat dices." +
            " Because of its length, the longsword enables you to attack your enemies diagonally.";

    public Longsword() {
        super("Longsword", description, 6, 350);
        super.setAttackDistance(2);
        super.setAttackBonus(3);
        super.setTwoHanded(true);
        super.setAttackDiagonally(true);
    }
}
