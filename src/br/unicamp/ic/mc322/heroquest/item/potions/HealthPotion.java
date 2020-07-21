package br.unicamp.ic.mc322.heroquest.item.potions;

import br.unicamp.ic.mc322.heroquest.item.HeapItem;
import br.unicamp.ic.mc322.heroquest.item.ItemClass;
import br.unicamp.ic.mc322.heroquest.walker.Walker;

public class HealthPotion extends HeapItem {
    private static final String DESCRIPTION = "A magic item able restore from 1 to 6 health points instantly";

    public HealthPotion() {
        super("Health potion", DESCRIPTION, 100, ItemClass.NEUTRAL);
    }

    @Override
    public void useItem(Walker proprietary) {
        int pointsToRestore = proprietary.rollRedDice();
        proprietary.restoreBodyPoints(pointsToRestore);
        proprietary.getManager().showMessage(String.format("The health potion healed %d points of life", pointsToRestore));
        proprietary.destroyItem(this);
    }
}
