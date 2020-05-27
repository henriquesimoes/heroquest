package br.unicamp.ic.mc322.heroquest.walker.monster;

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
