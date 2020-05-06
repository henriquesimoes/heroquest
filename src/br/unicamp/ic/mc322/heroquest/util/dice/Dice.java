package br.unicamp.ic.mc322.heroquest.util.dice;

import java.util.Random;

public class Dice {
    private static Random random = new Random();

    private int numberOfFaces;

    public Dice(int numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    /**
     * Rolls the dice.
     * @return The face index turned up.
     */
    int rollIndex() {
        return random.nextInt(numberOfFaces) + 1;
    }
}
