package br.unicamp.ic.mc322.heroquest;

public class Elf extends Hero {

    Elf(){
        super();
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = curBodyPoints =  6;
        mindPoints = 4;
        equipWeapon(new ShortSword());
    }
}
