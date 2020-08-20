package br.unicamp.ic.mc322.heroquest.walker.items.weapons;

import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.Attack;

public class LongSword extends Weapon {
    private static final String DESCRIPTION = "Long sword gives you a bonus of 3 combat dices.\n" +
            "Because of its length, the longsword enables you to attack your enemies diagonally.";

    public LongSword() {
        super("Longsword", DESCRIPTION, 6, 350, DurableItemClass.WARRIOR);
        setAttackBonus(3);
        setTwoHanded(true);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
    }
}
