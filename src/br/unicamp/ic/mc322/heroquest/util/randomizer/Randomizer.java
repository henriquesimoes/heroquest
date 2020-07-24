package br.unicamp.ic.mc322.heroquest.util.randomizer;

import java.util.Random;

/**
 * This class concentrates all the use of the class `Random`, and also simplifies the code,
 * since it is preventing the creation of a `Random` instance that is used for calling a single method
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

    /**
     * @return a random double in range [0, 1)
     */
    public static double nextDouble() {
        create();
        return random.nextDouble();
    }
}
