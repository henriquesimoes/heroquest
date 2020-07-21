package br.unicamp.ic.mc322.heroquest.walker.managers.player;

import br.unicamp.ic.mc322.heroquest.item.Item;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseItemAction;

public class UseItemPlayerAction extends UseItemAction {
    private WalkerPlayer walkerPlayer;
    private Walker walker;

    UseItemPlayerAction(WalkerPlayer walkerPlayer) {
        this.walkerPlayer = walkerPlayer;
        this.walker = walkerPlayer.getWalker();
    }

    public boolean execute() {
        Item chosenItem = chooseItem();
        if (chosenItem != null)
            chosenItem.useItem(walker);
        return false;
    }

    private Item chooseItem() {
        Item[] items = walker.getItems();
        return (Item) walkerPlayer.chooseDescribable(items, "Choose an item to use:");
    }
}
