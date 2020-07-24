package br.unicamp.ic.mc322.heroquest.walker.items.artifacts;

import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.Item;

public class GoldCoin extends Item {
    public GoldCoin(int value) {
        super("Gold", "You can use gold to buy items for your adventure", value);
    }

    @Override
    public void useItem(Walker proprietary) {
        proprietary.increaseBalance(getGoldCoinsValue());
    }
}
