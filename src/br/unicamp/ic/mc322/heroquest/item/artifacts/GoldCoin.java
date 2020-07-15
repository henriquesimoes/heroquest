package br.unicamp.ic.mc322.heroquest.item.artifacts;

import br.unicamp.ic.mc322.heroquest.item.CollectableItem;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class GoldCoin extends CollectableItem {
    public GoldCoin(int value) {
        super("Gold", "You can use gold to buy items for your adventure", value);
    }

    @Override
    public void useItem(Walker proprietary) {
        proprietary.increaseBalance(getGoldCoinsValue());
    }
}