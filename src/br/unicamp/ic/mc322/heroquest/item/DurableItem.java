package br.unicamp.ic.mc322.heroquest.item;

public class DurableItem extends Item {
    private int itemDurability;

    public DurableItem(String itemName, String itemDescription, int itemDurability, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);

        this.itemDurability = itemDurability;
    }

    public int getItemDurability() {
        return itemDurability;
    }

    public void updateItemDurability(int newItemDurability) {
        itemDurability =  newItemDurability;
    }
}