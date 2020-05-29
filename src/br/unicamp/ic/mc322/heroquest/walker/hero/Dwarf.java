package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.ShortSword;

public class Dwarf extends Hero {

    Dwarf(){
        super();
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints = 7;
        mindPoints = 3;

        Weapon curWeapon = new ShortSword();
        knapsack.put(curWeapon);

        equipWeapon(curWeapon);
    }
}
