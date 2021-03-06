package br.unicamp.ic.mc322.heroquest.walker.items.potions;

import br.unicamp.ic.mc322.heroquest.walker.Walker;
import br.unicamp.ic.mc322.heroquest.walker.items.HeapItem;

public class HealthPotion extends HeapItem {
    private static final String DESCRIPTION = "A magic item able restore from 1 to 6 health points instantly";

    public HealthPotion() {
        super("Health potion", DESCRIPTION, 100);
    }

    @Override
    public void useItem(Walker proprietary) {
        int pointsToRestore = proprietary.rollRedDice();
        proprietary.restoreBodyPoints(pointsToRestore);
        proprietary.getManager().showMessage(String.format("The health potion healed %d points of life", pointsToRestore));
        proprietary.destroyItem(this);
    }
}
