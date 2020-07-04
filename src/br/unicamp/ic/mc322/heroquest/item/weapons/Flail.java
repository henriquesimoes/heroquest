package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.AttackEnemy;
import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.AttackEnemyInLongDistanceRange;

public class Flail extends Weapon {
    private static final String DESCRIPTION = "Flail gives you a bonus of 3 combat dice." +
            " Flail this and struck your enemies with a powerful steel sphere with spikes.";

    public Flail() {
        super("Flail", DESCRIPTION, 4, 350);

        setAttackBonus(3);
        setTwoHanded(false);
        setAttackDiagonally(true);
        setNewSkill(new AttackEnemy("Hit", this));
        setNewSkill(new AttackEnemyInLongDistanceRange("Throw", this));
    }
}