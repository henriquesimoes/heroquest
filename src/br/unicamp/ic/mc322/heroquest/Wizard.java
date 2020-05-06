package br.unicamp.ic.mc322.heroquest;

public class Wizard extends Hero {

    Wizard(){
        super();
        attackDice = 1;
        defenseDice = 2;
        bodyPoints = 4;
        mindPoints = 6;
        equipWeapon(new Dagger());
    }
}
