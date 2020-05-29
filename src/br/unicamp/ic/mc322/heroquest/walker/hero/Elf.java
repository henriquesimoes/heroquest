package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.spells.SimpleHeal;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.ShortSword;

public class Elf extends Hero {

    Elf(){
        super();
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints =  6;
        mindPoints = 4;

        Weapon curWeapon = new ShortSword();
        knapsack.put(curWeapon);
        equipWeapon(curWeapon);

        addMagicSkill(new SimpleHeal());
    }
}
