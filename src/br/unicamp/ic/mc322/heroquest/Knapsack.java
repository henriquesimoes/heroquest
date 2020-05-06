package br.unicamp.ic.mc322.heroquest;

import java.util.LinkedHashMap;

public class Knapsack {
    private LinkedHashMap<CollectableItem, Integer> items;

    Knapsack(){
        items = new LinkedHashMap <>();
    }

    void put(CollectableItem item){
        Integer oldAmount = items.get(item);
        Integer curAmount = oldAmount == null ? 1 : oldAmount + 1;
        items.put(item, curAmount);
    }

    void remove(CollectableItem item){
        Integer curAmount = items.remove(item);
        curAmount --;
        if(curAmount > 0)
            items.put(item, curAmount);
    }
}
