package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class DurableItem extends Item {
    private int currentDurability, maximumDurability;

    protected DurableItem(String itemName, String itemDescription, int currentDurability, int goldCoinsValue, ItemClass itemClass) {
        super(itemName, itemDescription, goldCoinsValue, itemClass);

        this.currentDurability = this.maximumDurability = currentDurability;
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
