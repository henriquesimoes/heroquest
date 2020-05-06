package br.unicamp.ic.mc322.heroquest;

import java.util.LinkedHashMap;

public class Knapsack {
    private LinkedHashMap<CollectableItems, Integer> items;

    Knapsack(){
        items = new LinkedHashMap < CollectableItems, Integer >();
    }

    void put(CollectableItems item){
        Integer oldAmount = items.get(item);
        Integer curAmount = oldAmount == null ? 1 : oldAmount + 1;
        items.put(item, curAmount);
    }

    void remove(CollectableItems item){
        Integer curAmount = items.get(item);
        curAmount --;
        if(curAmount > 0)
            items.put(item, curAmount);
    }

}
