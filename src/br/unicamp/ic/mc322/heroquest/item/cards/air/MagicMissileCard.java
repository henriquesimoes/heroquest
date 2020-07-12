package br.unicamp.ic.mc322.heroquest.item.cards.air;

import br.unicamp.ic.mc322.heroquest.item.cards.AirSpellCard;
import br.unicamp.ic.mc322.heroquest.skills.magic.MagicMissile;

public class MagicMissileCard extends AirSpellCard {
    private static final String DESCRIPTION = "A magic item that supplies 1 unit of the spell MagicMissile.";

    public MagicMissileCard() {
        super("MagicMissileCard", DESCRIPTION, 100);
        spell = new MagicMissile();
    }
}
