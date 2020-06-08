package br.unicamp.ic.mc322.heroquest.item.baseitems;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class Item {
    private String itemName;
    private String itemDescription;
    private boolean existenceState = true;
    private int goldCoinsValue;

    public Item(String itemName, String itemDescription, int goldCoinsValue) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.goldCoinsValue = goldCoinsValue;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void changeExistenceState(boolean existence) {
        existenceState = existence;
    }

    public boolean getExistenceState(){
        return existenceState;
    }

    public abstract void useItem(Walker proprietary);
}
