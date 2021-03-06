package br.unicamp.ic.mc322.heroquest.walker.items.armors;

import br.unicamp.ic.mc322.heroquest.walker.items.Armor;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;

public class ChainMail extends Armor {
    private static final String DESCRIPTION = "A resistant light metal armor that gives you 3 extra defense dices";

    public ChainMail() {
        super("Chain mail", DESCRIPTION, 3, 8, 400, DurableItemClass.WARRIOR);
    }
}
