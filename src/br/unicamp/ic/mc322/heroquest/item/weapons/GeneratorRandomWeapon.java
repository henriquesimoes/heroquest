package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.weapons.armory.*;

import java.util.Random;

public class GeneratorRandomWeapon {
    public static Weapon getRandomWeapon() {
        Weapon weapon;
        int size = 7;
        Random rand = new Random();
        int choice = rand.nextInt(size);

        switch (choice){
            case 0:
                weapon = new BattleAxe();
                break;
            case 1:
                weapon = new Crossbow();
                break;
            case 2:
                weapon = new Dagger();
                break;
            case 3:
                weapon = new Flail();
                break;
            case 4:
                weapon = new LongSword();
                break;
            case 5:
                weapon = new ShortSword();
                break;
            case 6:
                weapon = new Staff();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }

        return weapon;
    }
}
