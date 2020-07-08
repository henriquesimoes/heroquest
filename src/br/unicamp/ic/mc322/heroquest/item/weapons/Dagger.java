package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.Attack;
import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.LongDistanceAttack;

public class Dagger extends Weapon {
    private static final String DESCRIPTION = "Dagger gives you a bonus of 1 combat dices." +
            " Daggers allow you to thrown at any enemies in your line of sight. You can thrown it" +
            "diagonally, but you can't not attack diagonally while stabbing a monster";

    public Dagger() {
        super("Dagger", DESCRIPTION, 6, 25);

        setAttackBonus(1);
        setTwoHanded(true);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
        addSkill(new LongDistanceAttack("Throw dagger", this));
    }
}
