package br.unicamp.ic.mc322.heroquest.walker.hero;

public class Barbarian extends Hero {

    Barbarian(){
        super();
        attackDice = 3;
        defenseDice = 2;
        maxBodyPoints = curBodyPoints = 8;
        mindPoints = 2;

        Weapon curWeapon = new LongSword();
        knapsack.put(curWeapon);

        equipWeapon(curWeapon);
    }
}
