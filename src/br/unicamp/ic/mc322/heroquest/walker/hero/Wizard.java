package br.unicamp.ic.mc322.heroquest.walker.hero;

import br.unicamp.ic.mc322.heroquest.item.spells.Fireball;
import br.unicamp.ic.mc322.heroquest.item.spells.MagicMissile;
import br.unicamp.ic.mc322.heroquest.item.spells.Teleport;
import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.Dagger;

public class Wizard extends Hero {

    Wizard(){
        super();
        final int numInitialDaggers = 3;
        final int numInitialMagicMissile = 3;

        attackDice = 1;
        defenseDice = 2;
        maxBodyPoints = currentBodyPoints =  4;
        mindPoints = 6;

        Weapon curWeapon = new Dagger();
        knapsack.put(curWeapon);
        equipWeapon(curWeapon);

        for(int i = 0; i < numInitialDaggers - 1; i++)
            knapsack.put(new Dagger());

        for (int i = 0; i < numInitialMagicMissile; i++)
            addMagicSkill(new MagicMissile());

        addMagicSkill(new Fireball());
        addMagicSkill(new Teleport());
    }
}
