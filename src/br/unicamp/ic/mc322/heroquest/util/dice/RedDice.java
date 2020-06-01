package br.unicamp.ic.mc322.heroquest.util.dice;

public class RedDice extends Dice {
    private static final int NUMBER_OF_FACES = 6;
    private int lastFaceAfterRoll;

    public RedDice() {
        super(NUMBER_OF_FACES);
    }

    public int roll() {
        return lastFaceAfterRoll;
    }
}
