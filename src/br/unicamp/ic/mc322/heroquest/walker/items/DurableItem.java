package br.unicamp.ic.mc322.heroquest.walker.items;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class DurableItem extends Item {
    protected DurableItemClass durableItemClass;
    private int currentDurability, maximumDurability;

    protected DurableItem(String itemName, String itemDescription, int currentDurability, int goldCoinsValue, DurableItemClass durableItemClass) {
        super(itemName, itemDescription, goldCoinsValue);

        this.currentDurability = this.maximumDurability = currentDurability;

        if (durableItemClass != DurableItemClass.NEUTRAL)
            this.itemDescription += "\nItem Class: " + durableItemClass.toString();
    }

    public void degradeByUse(Walker proprietary) {
        currentDurability--;
        if (currentDurability == 0)
            proprietary.destroyItem(this);
    }

    public String representationOnStatus() {
        return getName() + " - durability: " + getDurability();
    }

    private String getDurability() {
        int percentage = currentDurability * 100 / maximumDurability;
        return String.format("%d%%", percentage);
    }
}
