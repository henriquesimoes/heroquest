package br.unicamp.ic.mc322.heroquest.walker.items;

import java.util.Objects;

public abstract class HeapItem extends Item {
    protected HeapItem(String itemName, String itemDescription, int goldCoinsValue, ItemClass itemClass) {
        super(itemName, itemDescription, goldCoinsValue, itemClass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int compareTo(Object o) {
        Item that = (Item) o;
        return this.getName().compareTo(that.getName());
    }
}
