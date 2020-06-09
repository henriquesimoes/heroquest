package br.unicamp.ic.mc322.heroquest.util.random;

public class Random extends java.util.Random {
    public Random() {}

    public int random(int min, int max) {
        return nextInt(max - min) + min;
    }
}