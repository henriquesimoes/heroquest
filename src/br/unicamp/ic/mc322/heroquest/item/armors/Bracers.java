package br.unicamp.ic.mc322.heroquest.item.armors;

public class Bracers extends Armor {

    private static final String DESCRIPTION = "A protective pice that gives you 1 extra combat dice" +
            "in an attempt to increase your defense. Can be only used by wizards";

    public Bracers() {
        super("Bracers", DESCRIPTION, 1, 6, 200);
    }
}
