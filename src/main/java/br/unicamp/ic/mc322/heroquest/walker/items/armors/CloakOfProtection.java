package br.unicamp.ic.mc322.heroquest.walker.items.armors;

import br.unicamp.ic.mc322.heroquest.walker.items.Armor;
import br.unicamp.ic.mc322.heroquest.walker.items.DurableItemClass;

public class CloakOfProtection extends Armor {
    private static final String DESCRIPTION = "A protective (and stylish) piece that gives you 2 extra defense dices";

    public CloakOfProtection() {
        super("Cloak of protection", DESCRIPTION, 2, 6, 350, DurableItemClass.MAGICIAN);
    }
}
