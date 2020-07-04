package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

public class Knapsack {
    private LinkedHashMap<CollectableItem, Integer> items;

    Knapsack() {
        items = new LinkedHashMap<>();
    }

    public void put(CollectableItem item) {
        Integer oldAmount = items.get(item);
        Integer currentAmount = oldAmount == null ? 1 : oldAmount + 1;
        items.put(item, currentAmount);
    }

    public void remove(CollectableItem item) {
        Integer currentAmount = items.remove(item);
        if (currentAmount == null)
            throw new NoSuchElementException();

        currentAmount--;
        if (currentAmount > 0)
            items.put(item, currentAmount);
    }

    public ArrayList<CollectableItem> getItems() {
        ArrayList<CollectableItem> items = new ArrayList<>();

        for (CollectableItem item : this.items.keySet())
            items.add(item);

        return items;
    }
}