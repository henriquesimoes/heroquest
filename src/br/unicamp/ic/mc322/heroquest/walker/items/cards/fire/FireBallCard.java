package br.unicamp.ic.mc322.heroquest.walker.items.cards.fire;

import br.unicamp.ic.mc322.heroquest.walker.items.cards.FireSpellCard;
import br.unicamp.ic.mc322.heroquest.walker.skills.magic.FireBall;

public class FireBallCard extends FireSpellCard {
    private static final String DESCRIPTION = "A magic item that supplies 1 unit of the spell FireBall.";

    public FireBallCard() {
        super("Fireball card", DESCRIPTION, 100);

        spell = new FireBall();
    }
}
