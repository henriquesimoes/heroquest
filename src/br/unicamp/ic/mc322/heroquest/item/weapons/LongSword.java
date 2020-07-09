package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.skills.physical.Attack;

public class LongSword extends Weapon {
    private static final String DESCRIPTION = "Long sword gives you a bonus of 3 combat dices." +
            " Because of its length, the longsword enables you to attack your enemies diagonally.";

    public LongSword() {
        super("Longsword", DESCRIPTION, 6, 350);

        setAttackBonus(3);
        setTwoHanded(true);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
    }
}
