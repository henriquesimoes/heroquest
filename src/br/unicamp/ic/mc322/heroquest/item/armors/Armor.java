package br.unicamp.ic.mc322.heroquest.item.armors;

import br.unicamp.ic.mc322.heroquest.item.baseitems.DurableItem;

public class Armor extends DurableItem {
    private int defenseBonus;

    public Armor(String itemName, String itemDescription, int defenseBonus, int durability, int goldCoinsValue) {
        super(itemName, itemDescription, durability, goldCoinsValue);
        this.defenseBonus = defenseBonus;
    }

    public int getDefenceBonus() {
        return defenseBonus;
    }
}
