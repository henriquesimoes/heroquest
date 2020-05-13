package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.item.weapons.LongRangeWeapon;

public class Spell extends LongRangeWeapon {
    public Spell(String name, String description, int weaponDurability, int goldCoinsValue) {
        super(name, description, weaponDurability, goldCoinsValue);
    }
}
