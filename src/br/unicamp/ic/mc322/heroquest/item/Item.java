package br.unicamp.ic.mc322.heroquest.item;

import br.unicamp.ic.mc322.heroquest.walker.Walker;

public abstract class Item {
    private String itemName;
    private String itemDescription;
    private boolean existenceState;
    private int goldCoinsValue;

    protected Item(String itemName, String itemDescription, int goldCoinsValue) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.goldCoinsValue = goldCoinsValue;
        this.existenceState = true;
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

    public boolean getExistenceState() {
        return existenceState;
    }

    public abstract void useItem(Walker proprietary);

    public int getGoldCoinsValue(){
        return goldCoinsValue;
    }
}
