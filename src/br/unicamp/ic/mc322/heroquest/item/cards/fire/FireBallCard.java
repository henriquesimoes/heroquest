package br.unicamp.ic.mc322.heroquest.item.cards.fire;

import br.unicamp.ic.mc322.heroquest.item.cards.FireSpellCard;
import br.unicamp.ic.mc322.heroquest.skills.magic.FireBall;

public class FireBallCard extends FireSpellCard {
    private static final String DESCRIPTION = "A magic item that supplies 1 unit of the spell FireBall.";

    public FireBallCard() {
        super("FireBallCard", DESCRIPTION, 100);

        spell = new FireBall();
    }
}
