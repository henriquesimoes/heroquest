package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class DurableItem extends Item {
    private int itemDurability;

    protected DurableItem(String itemName, String itemDescription, int itemDurability, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);

        this.itemDurability = itemDurability;
    }

    public void degradeByUse(Walker proprietary) {
        itemDurability--;
        if (itemDurability == 0)
            proprietary.destroyItem(this);
    }
}
