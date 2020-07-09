package br.unicamp.ic.mc322.heroquest.item;

public abstract class CollectableItem extends Item {

    protected CollectableItem(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);
    }
}
