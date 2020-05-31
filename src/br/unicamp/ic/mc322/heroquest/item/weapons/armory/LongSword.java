package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.item.skills.weaponskills.AttackEnemy;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;

public class LongSword extends Weapon {
    private static final String description = "Longsword gives you a bonus of 3 combat dices." +
            " Because of its length, the longsword enables you to attack your enemies diagonally.";

    public LongSword() {
        super("Longsword", description, 6, 350);

        setAttackBonus(3);
        setTwoHanded(true);
        setAttackDiagonally(true);
        setNewSkill(new AttackEnemy("Golpear", this, 2));
    }
}
