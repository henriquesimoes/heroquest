package br.unicamp.ic.mc322.heroquest.item.spellCard.earthSpellCard;

import br.unicamp.ic.mc322.heroquest.skills.magicSkill.SimpleHeal;

public class SimpleHealCard extends EarthSpellCard {
    private static final String DESCRIPTION = "A magic item that supplies 1 unit of the spell SimpleHeal.";

    public SimpleHealCard() {
        super("SimpleHealCard", DESCRIPTION, 100);
        spell = new SimpleHeal();
    }
}

