package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells.Armory;

import br.unicamp.ic.mc322.heroquest.WeaponsAndSpells.Weapon;

public class LongSword extends Weapon {
    private static final String description = "Longsword gives you a bonus of 3 combat dice." +
            " Because of its length, the longsword enables you to attack your enemies diagonally.";

    public LongSword() {
        super("Longsword", description, 6);
        super.setAttackDistance(2);
        super.setAttackPower(3);
        super.setTwoHanded(true);
        super.setAttackDiagonally(true);
    }
}
