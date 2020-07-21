package br.unicamp.ic.mc322.heroquest.walker.items.weapons;

import br.unicamp.ic.mc322.heroquest.walker.items.Weapon;
import br.unicamp.ic.mc322.heroquest.walker.skills.physical.Attack;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Fists extends Weapon {
    private static final String DESCRIPTION = "A well-known primitive weapon: your own fists."
            + "They allow you to cause damage in enemies in the four directions";

    public Fists() {
        super("Fists", DESCRIPTION, Integer.MAX_VALUE, 0);
        addSkill(new Attack("Punch", this));
    }

    @Override
    public void degradeByUse(Walker proprietary) {
        // Fists not degrade
    }
}
