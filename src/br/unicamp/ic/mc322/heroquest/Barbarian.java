package br.unicamp.ic.mc322.heroquest;

public class Barbarian extends Hero {

    Barbarian(){
        super();
        attackDice = 3;
        defenseDice = 2;
        bodyPoints = 8;
        mindPoints = 2;
        equipWeapon(new LongSword());
    }
}
