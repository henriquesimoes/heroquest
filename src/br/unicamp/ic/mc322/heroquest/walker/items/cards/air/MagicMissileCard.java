package br.unicamp.ic.mc322.heroquest.walker.items.cards.air;

import br.unicamp.ic.mc322.heroquest.walker.items.cards.AirSpellCard;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.MagicMissile;

public class MagicMissileCard extends AirSpellCard {
    private static final String DESCRIPTION = "A magic item that supplies 1 unit of the spell Magic Missile.";

    public MagicMissileCard() {
        super("Magic missile card", DESCRIPTION, 100);
        spell = new MagicMissile();
    }
}
