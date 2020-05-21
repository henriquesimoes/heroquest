package br.unicamp.ic.mc322.heroquest.walker.hero;

public class Dwarf extends Hero {

    Dwarf(){
        super();
        attackDice = 2;
        defenseDice = 2;
        maxBodyPoints = curBodyPoints = 7;
        mindPoints = 3;
        equipWeapon(new ShortSword());
    }
}
