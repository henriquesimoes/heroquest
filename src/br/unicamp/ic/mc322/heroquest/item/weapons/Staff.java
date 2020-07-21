package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.ItemClass;
import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.skills.physical.Attack;

public class Staff extends Weapon {
    private static final String DESCRIPTION = "Staff gives you a bonus of 1 combat dice.\n" +
            "You can attack your enemies in a long distance range and two handed weapon.";

    public Staff() {
        super("Staff", DESCRIPTION, 3, 100, ItemClass.MAGICIAN);
        setAttackDistance(4);
        setAttackBonus(1);
        setTwoHanded(true);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
    }
}
