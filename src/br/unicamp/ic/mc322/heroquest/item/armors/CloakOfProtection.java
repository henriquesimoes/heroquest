package br.unicamp.ic.mc322.heroquest.item.armors;

import br.unicamp.ic.mc322.heroquest.item.Armor;

public class CloakOfProtection extends Armor {
    private static final String DESCRIPTION = "A protective (and stylish) piece that gives you 1 extra combat dice" +
            "in an attempt to increase your defense.\n" +
            "It can be only used by wizards";

    public CloakOfProtection() {
        super("Cloak of protection", DESCRIPTION, 2, 6, 350);
    }
}
