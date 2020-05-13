package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.Weapon;

public class Flail extends Weapon {
    private static final String description = "Flail gives you a bonus of 3 combat dice." +
            " Flail this and struck your enemies with a powerful steel sphere with spikes.";

    public Flail() {
        super("Flail", description, 4, 350);
        super.setAttackDistance(2);
        super.setAttackBonus(3);
        super.setTwoHanded(false);
        super.setAttackDiagonally(true);
    }
}
