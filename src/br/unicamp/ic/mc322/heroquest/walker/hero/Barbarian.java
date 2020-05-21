package br.unicamp.ic.mc322.heroquest.walker.hero;

public class Barbarian extends Hero {

    Barbarian(){
        super();
        attackDice = 3;
        defenseDice = 2;
        maxBodyPoints = curBodyPoints = 8;
        mindPoints = 2;
        equipWeapon(new LongSword());
    }
}
