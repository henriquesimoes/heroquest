package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.AttackEnemy;
import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.AttackEnemyInLongDistanceRange;
import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.PhysicalSkill;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;

public class Dagger extends Weapon {
    private static final String description = "Dagger gives you a bonus of 1 combat dices." +
            " Daggers allow you to thrown at any enemies in your line of sight. You can thrown it" +
            "diagonally, but you can't not attack diagonally while stabbing a monster";

    public Dagger() {
        super("Dagger", description, 6, 25);

        setAttackBonus(1);
        setTwoHanded(true);
        setAttackDiagonally(true);
        setNewSkill(new AttackEnemy("Golpear", this, 1));
        setNewSkill(new AttackEnemyInLongDistanceRange("Lançar adaga", this, 4));
    }
}
