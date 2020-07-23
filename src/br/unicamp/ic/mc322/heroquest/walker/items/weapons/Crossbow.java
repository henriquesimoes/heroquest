package br.unicamp.ic.mc322.heroquest.walker.items.weapons;

import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.ThrowProjectileAttack;

public class Crossbow extends Weapon {
    private static final String DESCRIPTION = "Crossbow gives you a bonus of 3 combat dices.\n" +
            "This is a long range weapon, you may fire at any monster in your sight.\n" +
            "However, you can't attack a monster adjacent to you.";

    public Crossbow() {
        super("Crossbow", DESCRIPTION, 6, 350, DurableItemClass.NEUTRAL);
        setAttackBonus(3);
        setTwoHanded(true);
        setAttackDiagonally(true);
        addSkill(new ThrowProjectileAttack("Fire crossbow", this));
    }
}
