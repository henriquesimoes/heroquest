package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.AttackEnemy;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;

public class ShortSword extends Weapon {
    private static final String DESCRIPTION = "Shortsword gives you a bonus of 2 combat dice." +
            " This is a light and useful weapon to fight against enemies (if they aren't too... strong) " +
            "in a short distance";

    public ShortSword() {
        super("Short sword", DESCRIPTION, 3, 150);

        setAttackBonus(2);
        setTwoHanded(false);
        setAttackDiagonally(false);
        setNewSkill(new AttackEnemy("Golpear", this, 1));
    }
}
