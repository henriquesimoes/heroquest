package br.unicamp.ic.mc322.heroquest.util.randomizer;

import java.util.Random;

public class Randomizer {
    private static Random random;

    private static void create(){
        if(random == null)
            random = new Random();
    }

    /**
     * @return an integer in range [min, max]
     */
    public static int randInt(int min, int max){
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * @return an integer in range [0, max)
     */
    public static int nextInt(int max){
        create();
        return random.nextInt(max);
    }
}
