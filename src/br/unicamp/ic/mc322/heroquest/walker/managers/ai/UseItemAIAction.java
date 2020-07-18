package br.unicamp.ic.mc322.heroquest.walker.managers.ai;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.util.randomizer.Randomizer;
import br.unicamp.ic.mc322.heroquest.walker.managers.UseItemAction;

public class UseItemAIAction extends UseItemAction {
    private WalkerAI walkerAI;

    UseItemAIAction(WalkerAI walkerAI) {
        super(walkerAI);
        this.walkerAI = walkerAI;
    }

    protected CollectableItem chooseItem(CollectableItem[] items) {
        return items.length == 0 ? null : items[Randomizer.nextInt(items.length)];
    }
}
