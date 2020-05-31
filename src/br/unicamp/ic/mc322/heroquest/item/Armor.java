package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;

public class Armor extends CollectableItem {
    public Armor(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);
    }

    public int getDefenceBonus() {
        return 0;
    }
}
