package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells.Armory;

import br.unicamp.ic.mc322.heroquest.WeaponsAndSpells.Weapon;

public class Shortsword extends Weapon {
    private static final String description = "Shortsword gives you a bonus of 2 combat dice." +
            " This is a light and useful weapon to fight against enemies (if they aren't too... strong) " +
            "in a short distance";

    public Shortsword() {
        super("Short sword", description, 3, 150);
        super.setAttackDistance(1);
        super.setAttackBonus(2);
        super.setTwoHanded(false);
        super.setAttackDiagonally(false);
    }
}
