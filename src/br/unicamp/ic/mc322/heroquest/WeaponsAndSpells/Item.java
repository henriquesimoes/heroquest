package br.unicamp.ic.mc322.heroquest.WeaponsAndSpells;

public class Item {
    private String itemName;
    private String itemDescription;
    private int itemDurability;

    public Item(String itemName, String itemDescription, int itemDurability) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemDurability = itemDurability;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemDurability() {
        return itemDurability;
    }

    public void updateItemDurability(int newItemDurability) {
        itemDurability =  newItemDurability;
    }

}
