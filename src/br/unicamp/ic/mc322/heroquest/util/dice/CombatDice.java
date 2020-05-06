package br.unicamp.ic.mc322.heroquest.util.dice;

public class CombatDice extends Dice {

    public static final int NUMBER_OF_SKULLS = 3;
    public static final int NUMBER_OF_HERO_SHIELDS = 2;
    public static final int NUMBER_OF_MONSTER_SHIELDS = 1;

    public CombatDice() {
        super(NUMBER_OF_HERO_SHIELDS + NUMBER_OF_MONSTER_SHIELDS + NUMBER_OF_SKULLS);
    }

    public CombatDiceFace roll() {
        int index = rollIndex();

        if (index <= NUMBER_OF_SKULLS)
            return CombatDiceFace.SKULL;

        if (index <= NUMBER_OF_SKULLS + NUMBER_OF_MONSTER_SHIELDS)
            return CombatDiceFace.MONSTER_SHIELD;

        return CombatDiceFace.HERO_SHIELD;
    }
}
