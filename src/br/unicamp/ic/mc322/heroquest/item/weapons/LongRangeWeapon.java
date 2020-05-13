package br.unicamp.ic.mc322.heroquest.item.weapons;

import br.unicamp.ic.mc322.heroquest.item.Weapon;

public class LongRangeWeapon extends Weapon {
    public int throwDistance;

    public LongRangeWeapon(String name, String description, int weaponDurability, int goldCoinsValue) {
        super(name, description, weaponDurability, goldCoinsValue);
    }

    public void setThrowDistance(int distance) {
        this.throwDistance = distance;
    }

    public int getThrowDistance() {
        return throwDistance;
    }
}
