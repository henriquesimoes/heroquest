package br.unicamp.ic.mc322.heroquest.item;

public abstract class HeapItem extends Item {
    protected HeapItem(String itemName, String itemDescription, int goldCoinsValue, ItemClass itemClass) {
        super(itemName, itemDescription, goldCoinsValue, itemClass);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof HeapItem) {
            HeapItem heapItem = (HeapItem) object;
            return heapItem.getName().equals(this.getName());
        }

        return false;
    }
}
