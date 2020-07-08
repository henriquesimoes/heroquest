package br.unicamp.ic.mc322.heroquest.item.baseitems;

public abstract class HeapItem extends CollectableItem {
    public HeapItem(String itemName, String itemDescription, int goldCoinsValue) {
        super(itemName, itemDescription, goldCoinsValue);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof HeapItem) {
            HeapItem heapItem = (HeapItem)object;
            return heapItem.getItemName().equals(this.getItemName());
        }

        return false;
    }
}
