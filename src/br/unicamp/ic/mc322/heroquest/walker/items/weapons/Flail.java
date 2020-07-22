package br.unicamp.ic.mc322.heroquest.walker.items.weapons;

import br.unicamp.ic.mc322.heroquest.walker.items.ItemClass;
import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.Attack;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.ThrowWeaponAttack;

public class Flail extends Weapon {
    private static final String DESCRIPTION = "Flail gives you a bonus of 3 combat dice.\n" +
            "Flail this and struck your enemies with a powerful steel sphere with spikes.";

    public Flail() {
        super("Flail", DESCRIPTION, 4, 350, ItemClass.WARRIOR);
        setAttackBonus(3);
        setTwoHanded(false);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
        addSkill(new ThrowWeaponAttack("Throw", this));
    }
}
