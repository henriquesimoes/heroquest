package br.unicamp.ic.mc322.heroquest.item.armors;

public class Helmet extends Armor {
    private static final String DESCRIPTION = "A protective headpice that gives you 1 extra combat dice" +
            "in an attempt to increase your defense.";

    public Helmet() {
        super("Helmet", DESCRIPTION, 1, 8, 120);
    }
}
