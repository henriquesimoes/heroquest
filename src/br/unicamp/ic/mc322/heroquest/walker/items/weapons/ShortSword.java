package br.unicamp.ic.mc322.heroquest.walker.items.weapons;

import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.Attack;

public class ShortSword extends Weapon {
    private static final String DESCRIPTION = "Short sword gives you a bonus of 2 combat dice." +
            " This is a light and useful weapon to fight against enemies (if they aren't too... strong) " +
            "in a short distance";

    public ShortSword() {
        super("Short sword", DESCRIPTION, 3, 150);

        setAttackBonus(2);
        setTwoHanded(false);
        setAttackDiagonally(false);
        addSkill(new Attack("Hit", this));
    }
}
