package br.unicamp.ic.mc322.heroquest.walker;

import br.unicamp.ic.mc322.heroquest.item.baseitems.CollectableItem;

import java.util.LinkedHashMap;

public class Knapsack {
    private LinkedHashMap<CollectableItem, Integer> items;

    Knapsack(){
        items = new LinkedHashMap <>();
    }

    public void put(CollectableItem item){
        Integer oldAmount = items.get(item);
        Integer curAmount = oldAmount == null ? 1 : oldAmount + 1;
        items.put(item, curAmount);
    }

    public void remove(CollectableItem item){
        Integer curAmount = items.remove(item);
        if(curAmount == null){
            System.out.println("Fatal Error: Try remove an item that not exists");
            System.exit(1);
        }
        curAmount --;
        if(curAmount > 0)
            items.put(item, curAmount);
    }
}
