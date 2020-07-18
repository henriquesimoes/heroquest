package br.unicamp.ic.mc322.heroquest.walker.managers;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.WalkerManager;

public abstract class UseItemAction implements Action {
    private WalkerManager walkerManager;

    public UseItemAction(WalkerManager walkerManager) {
        this.walkerManager = walkerManager;
    }

    @Override
    public String getDescription() {
        return "Use items";
    }

    @Override
    public boolean execute() {
        Walker walker = walkerManager.getWalker();
        CollectableItem[] items = walker.getItems();
        CollectableItem chosenItem = chooseItem(items);
        if (chosenItem != null)
            chosenItem.useItem(walker);
        return false;
    }

    protected abstract CollectableItem chooseItem(CollectableItem[] items);
}
