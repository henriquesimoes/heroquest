package br.unicamp.ic.mc322.heroquest.item.weapons.armory;

import br.unicamp.ic.mc322.heroquest.skills.physicalSkill.AttackEnemy;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;

public class BattleAxe extends Weapon {
    private static final String DESCRIPTION = "A heavy axe that gives you a bonus of 4 combat dices." +
            " This is a strong two handed weapon. You may not use a shield when using it, go fight!";

    public BattleAxe() {
        super("Battle axe", DESCRIPTION, 7, 450);

        setAttackBonus(4);
        setTwoHanded(true);
        setAttackDiagonally(false);
        setNewSkill(new AttackEnemy("Golpear", this, 1));
    }
}
