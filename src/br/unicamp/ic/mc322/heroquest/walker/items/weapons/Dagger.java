package br.unicamp.ic.mc322.heroquest.walker.items.weapons;

import br.unicamp.ic.mc322.heroquest.walker.items.ItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.Attack;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.ThrowWeaponAttack;

public class Dagger extends Weapon {
    private static final String DESCRIPTION = "Dagger gives you a bonus of 1 combat dices.\n" +
            "It is possible to throw them at any enemies in your line of sight.\n" +
            "You can throw it diagonally, but you can't not attack diagonally to stab a monster";

    public Dagger() {
        super("Dagger", DESCRIPTION, 6, 25, ItemClass.NEUTRAL);
        setAttackBonus(1);
        setTwoHanded(false);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
        addSkill(new ThrowWeaponAttack("Throw dagger", this));
    }
}
