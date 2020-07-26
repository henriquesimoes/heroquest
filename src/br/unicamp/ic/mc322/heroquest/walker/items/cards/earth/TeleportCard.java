package br.unicamp.ic.mc322.heroquest.walker.items.cards.earth;

import br.unicamp.ic.mc322.heroquest.walker.items.cards.EarthSpellCard;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.Teleport;

public class TeleportCard extends EarthSpellCard {
    private static final String DESCRIPTION = "A magic item that supplies 1 unit of the spell Teleport.";

    public TeleportCard() {
        super("Teleport card", DESCRIPTION, 100);

        spell = new Teleport();
    }
}
