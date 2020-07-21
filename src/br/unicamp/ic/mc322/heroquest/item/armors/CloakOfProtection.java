package br.unicamp.ic.mc322.heroquest.item.armors;

import br.unicamp.ic.mc322.heroquest.item.Armor;
import br.unicamp.ic.mc322.heroquest.item.ItemClass;

public class CloakOfProtection extends Armor {
    private static final String DESCRIPTION = "A protective (and stylish) piece that gives you 1 extra combat dice" +
            "in an attempt to increase your defense.";

    public CloakOfProtection() {
        super("Cloak of protection", DESCRIPTION, 2, 6, 350, ItemClass.MAGICIAN);
    }
}
