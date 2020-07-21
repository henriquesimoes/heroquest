package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.player.Describable;

public abstract class Item implements Describable {
    protected ItemClass itemClass;
    private String itemName;
    private String itemDescription;
    private int goldCoinsValue;

    protected Item(String itemName, String itemDescription, int goldCoinsValue, ItemClass itemClass) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.goldCoinsValue = goldCoinsValue;
        this.itemClass = itemClass;
        if (itemClass != ItemClass.NEUTRAL)
            this.itemDescription += "\nItem Class: " + itemClass.toString();
    }

    public String getName() {
        return itemName;
    }

    public String getDescription() {
        return itemDescription;
    }

    public abstract void useItem(Walker proprietary);

    public int getGoldCoinsValue() {
        return goldCoinsValue;
    }
}
