package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.Weapon;

public class Globin extends Monster {
    public Globin(){
        super();
        final int numInitialDaggers = 2;
        attackDice = 3;
        defenseDice = 1;
        maxBodyPoints = curBodyPoints = 2;
        mindPoints = 2;

        Weapon curWeapon = new Dagger();
        knapsack.put(curWeapon);
        equipWeapon(curWeapon);

        for(int i = 0; i < numInitialDaggers - 1; i++)
            knapsack.put(new Dagger());

    }
}
