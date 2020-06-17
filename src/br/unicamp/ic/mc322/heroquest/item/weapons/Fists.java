package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.AttackEnemy;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class Fists extends Weapon {
    private static final String DESCRIPTION = "The primitives weapon of the walker, your own fists."
                                            + "Allow cause damage in enemies in the four directions";

    public Fists() {
        super("Fists", DESCRIPTION, Integer.MAX_VALUE, 0);
        setNewSkill(new AttackEnemy("Punch", this));
    }

    @Override
    public void degradeByUse(Walker proprietary){}//Fists not degrade
}
