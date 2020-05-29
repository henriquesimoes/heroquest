package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.weapons.Weapon;
import br.unicamp.ic.mc322.heroquest.item.weapons.armory.Dagger;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.WalkerAI;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.attackBehavior.Bloodthirsty;
import br.unicamp.ic.mc322.heroquest.walker.walkerManager.walkerAI.movimentBehavior.Follower;

public class Globin extends Monster {
    public Globin(){
        super();
        walkerManager = new WalkerAI(new Follower(), new Bloodthirsty());
        final int numInitialDaggers = 2;
        attackDice = 3;
        defenseDice = 1;
        maxBodyPoints = currentBodyPoints = 2;
        mindPoints = 2;

        Weapon curWeapon = new Dagger();
        knapsack.put(curWeapon);
        equipWeapon(curWeapon);

        for(int i = 0; i < numInitialDaggers - 1; i++)
            knapsack.put(new Dagger());

    }
}
