package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.Weapon;
import br.unicamp.ic.mc322.heroquest.skills.physical.Attack;
import br.unicamp.ic.mc322.heroquest.skills.physical.ThrowWeaponAttack;

public class Flail extends Weapon {
    private static final String DESCRIPTION = "Flail gives you a bonus of 3 combat dice.\n" +
            "Flail this and struck your enemies with a powerful steel sphere with spikes.";

    public Flail() {
        super("Flail", DESCRIPTION, 4, 350);

        setAttackBonus(3);
        setTwoHanded(false);
        setAttackDiagonally(true);
        addSkill(new Attack("Hit", this));
        addSkill(new ThrowWeaponAttack("Throw", this));
    }
}
