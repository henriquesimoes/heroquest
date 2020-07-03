package br.unicamp.ic.mc322.heroquest.util.randomizer;

import java.util.Random;

public class Randomizer {
    private static Random random;

    public static int randInt(int min, int max){
        if(random == null)
            random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


    public static int nextInt(int max){
        if(random == null)
            random = new Random();
        return random.nextInt(max);
    }
}
