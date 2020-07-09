package br.unicamp.ic.mc322.heroquest.util.dice;

import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

abstract class Dice {
    private int numberOfFaces;

    public Dice(int numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    /**
     * Rolls the dice.
     * @return The face index turned up.
     */
    int rollIndex() {
        return Randomizer.randInt(0, numberOfFaces);
    }
}
