package br.unicamp.ic.mc322.heroquest.walker.hero;

public class Elf extends Hero {

    Elf(){
        super();
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = curBodyPoints =  6;
        mindPoints = 4;

        Weapon curWeapon = new ShortSword();
        knapsack.put(curWeapon);
        equipWeapon(curWeapon);

        knapsack.put(new SimpleHealCard());
    }
}
