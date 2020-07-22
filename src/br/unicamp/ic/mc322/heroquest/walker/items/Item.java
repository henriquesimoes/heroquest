package br.unicamp.ic.mc322.heroquest.walker.items;

import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.player.Describable;

public abstract class Item implements Describable, Comparable {
    protected ItemClass itemClass;
    private String itemName, itemDescription;
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

    @Override
    public int compareTo(Object o) {
        Item that = (Item) o;
        int order = this.getName().compareTo(that.getName());
        return order != 0 ? order : Integer.compare(this.hashCode(), that.hashCode());
    }
}
