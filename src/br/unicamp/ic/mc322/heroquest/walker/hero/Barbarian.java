package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.LongSword;

public class Barbarian extends Hero {

    Barbarian(){
        super();
        attackDice = 3;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints = 8;
        mindPoints = 2;

        Weapon curWeapon = new LongSword();
        knapsack.put(curWeapon);

        equipWeapon(curWeapon);
    }
}
