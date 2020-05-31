package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.AttackEnemyInLongDistanceRange;
import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;

public class Crossbow extends Weapon {
    private static final String description = "Crossbow gives you a bonus of 3 combat dices." +
            " This is a long range weapon, you may fire at any monster in your sight. However," +
            " you can't attack a monster adjacent to you.";

    public Crossbow() {
        super("Crossbow", description, 6, 350);

        setAttackBonus(3);
        setTwoHanded(true);
        setAttackDiagonally(true);
        setNewSkill(new AttackEnemyInLongDistanceRange("Atirar flecha", this, 10));
    }
}
