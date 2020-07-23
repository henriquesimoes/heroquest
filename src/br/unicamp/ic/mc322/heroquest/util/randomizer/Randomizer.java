package br.unicamp.ic.mc322.heroquest.util.randomizer;

import java.util.Random;

/**
 * This class concentrates all use of class Random, and also simplifies the code,
 * since preventing the creates of an instance of the Random that is used for calls only a method
 */
public class Randomizer {
    private static Random random;

    private static void create() {
        if (random == null)
            random = new Random();
    }

    /**
     * @return a random integer in range [min, max]
     */
    public static int randInt(int min, int max) {
        create();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * @return a random integer in range [0, max)
     */
    public static int nextInt(int max) {
        create();
        return random.nextInt(max);
    }

    /**
     * @return a random boolean
     */
    public static boolean nextBoolean() {
        create();
        return random.nextBoolean();
    }

    public static double nextDouble() {
        create();
        return random.nextDouble();
    }
}
