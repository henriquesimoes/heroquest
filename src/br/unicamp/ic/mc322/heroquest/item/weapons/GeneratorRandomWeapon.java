package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.weapons.armory.*;

import java.util.Random;

public class GeneratorRandomWeapon {
    public static Weapon getRandomWeapon() {
        Random rand = new Random();

        Weapon[] possibleWeapons = {
                new BattleAxe(),
                new Crossbow(),
                new Dagger(),
                new Flail(),
                new LongSword(),
                new ShortSword(),
                new Staff(),
        };

        int choice = rand.nextInt(possibleWeapons.length);

        return possibleWeapons[choice];
    }
}
