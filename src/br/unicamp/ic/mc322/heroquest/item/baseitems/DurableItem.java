package br.unicamp.ic.mc322.heroquest.item.baseitems;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class DurableItem extends CollectableItem {
    private int itemDurability;

    public DurableItem(String itemName, String itemDescription, int itemDurability, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);

        this.itemDurability = itemDurability;
    }

    public int getItemDurability() {
        return itemDurability;
    }

    public void decreaseItemDurability() {
        itemDurability--;
        if (itemDurability == 0)
            changeExistenceState(false);
    }

    public void degradeByUse(Walker proprietary){
        decreaseItemDurability();
        if (!getExistenceState())
            proprietary.destroyItem(this);
    }
}
