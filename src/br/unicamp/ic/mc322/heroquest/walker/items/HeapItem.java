package br.unicamp.ic.mc322.heroquest.walker.items;

import java.util.Objects;


// Two HeapItems are the same if they have the same name
public abstract class HeapItem extends Item {
    protected HeapItem(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);
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
