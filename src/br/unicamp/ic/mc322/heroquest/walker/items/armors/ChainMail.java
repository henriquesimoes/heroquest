package br.unicamp.ic.mc322.heroquest.walker.items.armors;

import br.unicamp.ic.mc322.heroquest.walker.items.Armor;
import br.unicamp.ic.mc322.heroquest.walker.items.ItemClass;

public class ChainMail extends Armor {
    private static final String DESCRIPTION = "A resistant light metal armor that can be combined with a helmet or shield" +
            "to great increase your defense.\n" +
            "Because it is so heavy, you may only roll 1 movement dice while wearing it";

    public ChainMail() {
        super("Chain mail", DESCRIPTION, 3, 8, 400, ItemClass.WARRIOR);
    }
}
