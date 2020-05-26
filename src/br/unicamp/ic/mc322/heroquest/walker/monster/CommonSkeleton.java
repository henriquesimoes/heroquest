package br.unicamp.ic.mc322.heroquest.walker.monster;

import br.unicamp.ic.mc322.heroquest.item.Weapon;

public class CommonSkeleton extends Skeleton {

    CommonSkeleton(){
        super();
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = curBodyPoints = 2;
        mindPoints = 1;
        // TODO:: See how the skeleton generate your weapon
        knapsack.put(weapon);
        equipWeapon(weapon);
    }
}
