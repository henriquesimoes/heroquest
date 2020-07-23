package br.unicamp.ic.mc322.heroquest.walker.items.weapons;

import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.Attack;

public class Staff extends Weapon {
    private static final String DESCRIPTION = "Staff gives you a bonus of 1 combat dice.\n" +
            "You can attack your enemies in a long distance range and two handed weapon.";

    public Staff() {
        super("Staff", DESCRIPTION, 3, 100, DurableItemClass.MAGICIAN);
        setAttackBonus(1);
        setTwoHanded(true);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
    }
}
