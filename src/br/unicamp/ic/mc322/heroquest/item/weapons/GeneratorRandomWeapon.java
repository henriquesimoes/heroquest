package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.weapons.armory.*;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;

public class GeneratorRandomWeapon {
    public static Weapon getRandomWeapon() {
        Weapon[] possibleWeapons = {
                new BattleAxe(),
                new Crossbow(),
                new Dagger(),
                new Flail(),
                new LongSword(),
                new ShortSword(),
                new Staff(),
        };

        int choice = Randomizer.randInt(0, possibleWeapons.length - 1);

        return possibleWeapons[choice];
    }
}
