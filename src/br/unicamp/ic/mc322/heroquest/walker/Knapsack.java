package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.walker.items.Item;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

public class Knapsack {
    private java.util.Map<Item, Integer> items;

    Knapsack() {
        items = new LinkedHashMap<>();
    }

    public void put(Item item) {
        Integer oldAmount = items.get(item);
        Integer currentAmount = oldAmount == null ? 1 : oldAmount + 1;
        items.put(item, currentAmount);
    }

    public void remove(Item item) {
        Integer currentAmount = items.remove(item);
        if (currentAmount == null)
            throw new NoSuchElementException();

        currentAmount--;
        if (currentAmount > 0)
            items.put(item, currentAmount);
    }

    Item[] getItemList() {
        return items.keySet().toArray((new Item[0]));
    }

    java.util.Map<Item, Integer> getItems() {
        return items;
    }
}
