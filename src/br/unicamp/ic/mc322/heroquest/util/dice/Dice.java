package br.unicamp.ic.mc322.heroquest.util.dice;

import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

abstract class Dice {
    private final int numberOfFaces;

    public Dice(int numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    /**
     * Rolls the dice.
     *
     * @return The face index turned up.
     */
    protected int rollIndex() {
        return Randomizer.randInt(1, numberOfFaces);
    }
}
