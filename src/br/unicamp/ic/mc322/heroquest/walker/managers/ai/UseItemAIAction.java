package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.item.Item;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseItemAction;

public class UseItemAIAction extends UseItemAction {
    private WalkerAI walkerAI;
    private Walker walker;

    UseItemAIAction(WalkerAI walkerAI) {
        this.walkerAI = walkerAI;
        this.walker = walkerAI.getWalker();
    }

    public boolean execute() {
        Item[] items = walker.getItems();
        Item chosenItem = chooseItem(items);
        if (chosenItem != null)
            chosenItem.useItem(walker);
        return false;
    }

    protected Item chooseItem(Item[] items) {
        return items.length == 0 ? null : items[Randomizer.nextInt(items.length)];
    }
}
